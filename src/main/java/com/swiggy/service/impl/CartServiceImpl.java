package com.swiggy.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiggy.dto.request.AddToCartRequest;
import com.swiggy.dto.response.CartItemResponse;
import com.swiggy.dto.response.CartResponse;
import com.swiggy.entity.Cart;
import com.swiggy.entity.CartItem;
import com.swiggy.entity.FoodItem;
import com.swiggy.entity.User;
import com.swiggy.exception.FoodNotAvailableException;
import com.swiggy.repository.CartItemRepository;
import com.swiggy.repository.CartRepository;
import com.swiggy.repository.FoodRepository;
import com.swiggy.repository.UserRepository;
import com.swiggy.service.CartService;

@Service
public class CartServiceImpl implements CartService{

    private final CartItemRepository cartItemRepository;
	
	
	@Autowired
	FoodRepository foodRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CartRepository cartRepository;

    CartServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }
	
	@Override
	public String addToCart(AddToCartRequest request, String userEmail) {
		
		//1. Get User
		
		User user=userRepository.findByEmail(userEmail).orElseThrow(()->new RuntimeException("User not Found"));
		
		//2. Get or Create Cart
		
		Cart cart=cartRepository.findByUser(user).orElseGet(()-> {
			Cart newCart=new Cart();
			newCart.setUser(user);
			
			return cartRepository.save(newCart);
		});
		
		
		// 3. Get Food
		FoodItem food=foodRepository.findById(request.getFoodId()).orElseThrow(()-> new FoodNotAvailableException("Food Not Found"));
		
		// 4. Check If Item is alraedy available
		
		Optional<CartItem> existingItem=cart.getItems().stream().filter(item->item.getFood().getId().equals(food.getId())).findFirst();
		
		if(existingItem.isPresent()) {
			//Update Quantity
			
			CartItem item=existingItem.get();
			item.setQuantity(item.getQuantity()+request.getQuantity());
			item.setPrice(item.getQuantity()*food.getPrice());
		}else {
			// Add new item
			
			CartItem newItem=new CartItem();
			newItem.setCart(cart);
			newItem.setFood(food);
			newItem.setQuantity(request.getQuantity());
			newItem.setPrice(request.getQuantity()*food.getPrice());
			cart.getItems().add(newItem);
		}
		
		// 5. Recalcualte total 
		
		double total=cart.getItems()
				.stream()
				.mapToDouble(CartItem::getPrice)
				.sum();
		
			cart.setTotalPrice(total);
			cartRepository.save(cart);
		
		return "Item Added to Cart";
	}
	
	
	@Override
	public CartResponse getCartForUser(String email) {
		
		//1. fetch User
		
				User user=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not Found"));
				
				//2. Get or Create Cart
				
				Cart cart=cartRepository.findByUser(user).orElseGet(()-> {
					Cart newCart=new Cart();
					newCart.setUser(user);
					
					return cartRepository.save(newCart);
				});
				
				// 3. Get food from existing cart
				
				List<CartItem> existingItem=cart.getItems();
				
				double totalPrice=0;
				List<CartItemResponse> response=new ArrayList<>();
				
				for(CartItem items:existingItem) {
					FoodItem food=items.getFood();
					
					double latestPrice=food.getPrice();
					int quantity=items.getQuantity();
					
					double itemTotal=latestPrice*quantity;
					
					items.setPrice(itemTotal);
					totalPrice+=itemTotal;
					
					response.add(
							new CartItemResponse(food.getId()
							,food.getName()
							,quantity,
							itemTotal)
							);
					
					
				}
				cart.setTotalPrice(totalPrice);
				cartRepository.save(cart);
				
				return new CartResponse(cart.getId(),response,totalPrice);
				
				
	}
	@Override
	public CartResponse updateCartItem(String email, Long cartItemId, int quantity) {

	    // 1. Validate quantity
	    if (quantity < 0) {
	        throw new RuntimeException("Quantity cannot be negative");
	    }

	    // 2. Fetch user
	    User user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    // 3. Fetch cart item
	    CartItem cartItem = cartItemRepository.findById(cartItemId)
	            .orElseThrow(() -> new RuntimeException("CartItem not found"));

	    // 4. Ownership validation
	    if (!cartItem.getCart().getUser().getId().equals(user.getId())) {
	        throw new RuntimeException("Unauthorized access to cart item");
	    }

	    // 5. Apply business logic
	    if (quantity == 0) {
	        cartItemRepository.delete(cartItem);
	        return getCartForUser(email);
	    }

	    // 6. Update quantity and price
	    cartItem.setQuantity(quantity);

	    double updatedPrice = quantity * cartItem.getFood().getPrice();
	    cartItem.setPrice(updatedPrice);

	    cartItemRepository.save(cartItem);

	    // 7. Return updated cart
	    return getCartForUser(email);
	}
	
	public CartResponse removeCartItem(String email, Long cartItemId) {

	    // 1. Fetch user
	    User user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    // 2. Fetch cart item
	    CartItem cartItem = cartItemRepository.findById(cartItemId)
	            .orElseThrow(() -> new RuntimeException("CartItem not found"));

	    // 3. Ownership validation
	    if (!cartItem.getCart().getUser().getId().equals(user.getId())) {
	        throw new RuntimeException("Unauthorized access to cart item");
	    }

	    // 4. Delete item
	    cartItemRepository.delete(cartItem);

	    // 5. Return updated cart
	    return getCartForUser(email);
	}
	
	@Override
	public CartResponse clearCart(String email) {
		//1. Fethch User
		
		User user=userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User Not Found"));
		
		//2. Fetch cart
		
		Cart cart=cartRepository.findByUser(user).orElseThrow(()-> new RuntimeException("Cart is not existed"));
		
		cart.getItems().clear();
		cart.setTotalPrice(0);
		
		cartRepository.save(cart);
		
		return getCartForUser(email);
		
	}
	
}
