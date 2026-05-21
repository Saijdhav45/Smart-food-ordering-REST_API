package com.swiggy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.swiggy.dto.response.OrderItemResponse;
import com.swiggy.dto.response.OrderResponse;
import com.swiggy.entity.Cart;
import com.swiggy.entity.CartItem;
import com.swiggy.entity.Order;
import com.swiggy.entity.OrderItem;
import com.swiggy.entity.OrderStatus;
import com.swiggy.entity.User;
import com.swiggy.repository.CartItemRepository;
import com.swiggy.repository.CartRepository;
import com.swiggy.repository.OrderRepository;
import com.swiggy.repository.UserRepository;
import com.swiggy.service.OrderService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	CartItemRepository cartItemRepository;

	@Autowired
	OrderRepository orderRepository;

	@Transactional
	public OrderResponse placeOrder(String email) {

		// 1. Fetch user
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		// 2. Fetch cart
		Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Cart not found"));

		// 3. Validate cart
		if (cart.getItems().isEmpty()) {
			throw new RuntimeException("Cart is empty");
		}

		// 4. Create order
		Order order = new Order();
		order.setUser(user);
		order.setStatus(OrderStatus.CREATED);

		// 5. Convert CartItems → OrderItems
		List<OrderItem> orderItems = new ArrayList<>();
		double totalAmount = 0;

		for (CartItem cartItem : cart.getItems()) {

			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setFoodName(cartItem.getFood().getName());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setPrice(cartItem.getPrice());

			totalAmount += cartItem.getPrice();

			orderItems.add(orderItem);
		}

		// 6. Set data in order
		order.setOrderItems(orderItems);
		order.setTotalAmount(totalAmount);

		// 7. Save order
		orderRepository.save(order);

		// 8. Clear cart
		cart.getItems().clear();
		cart.setTotalPrice(0);
		cartRepository.save(cart);
		return mapToOrderResponse(order);

//		OrderResponse orderResponse = new OrderResponse();
//		orderResponse.setOrderId(order.getId());
//		orderResponse.setTotalAmount(order.getTotalAmount());
//		orderResponse.setStatus(order.getStatus());
//		List<OrderItemResponse> itemResponses = new ArrayList<>();
//
//		for (OrderItem item : order.getOrderItems()) {
//
//			OrderItemResponse response = new OrderItemResponse();
//			response.setFoodName(item.getFoodName());
//			response.setQuantity(item.getQuantity());
//			response.setPrice(item.getPrice());
//
//			itemResponses.add(response);
//		}
//
//		orderResponse.setItems(itemResponses);
//		return orderResponse;
	}

	@Override
	public Page<OrderResponse> getOrdersForUser(String email, OrderStatus orderStatus, Pageable pageable) {

		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
		Page<Order> orderPage;
		if (orderStatus != null) {
			orderPage = orderRepository.findByUserAndStatus(user, orderStatus, pageable);
		} else {
			orderPage = orderRepository.findByUser(user, pageable);
		}

		return orderPage.map(order -> {

			List<OrderItemResponse> itemResponses = new ArrayList<>();

			if (order.getOrderItems() != null) {
				for (OrderItem item : order.getOrderItems()) {

					OrderItemResponse itemResponse = new OrderItemResponse();
					itemResponse.setFoodName(item.getFoodName());
					itemResponse.setQuantity(item.getQuantity());
					itemResponse.setPrice(item.getPrice());

					itemResponses.add(itemResponse);
				}
			}

			OrderResponse response = new OrderResponse();
			response.setOrderId(order.getId());
			response.setTotalAmount(order.getTotalAmount());
			response.setStatus(order.getStatus());
			response.setItems(itemResponses);
			response.setCreatedAt(order.getCreatedAt());

			return response;
		});
	}

	public OrderResponse updateOrderStatus(Long orderId, OrderStatus newStatus) {

		// 1. Fetch order
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

		OrderStatus currentStatus = order.getStatus();

		// 2. Validate transition
		if (currentStatus == OrderStatus.CREATED && newStatus == OrderStatus.CONFIRMED) {
			order.setStatus(OrderStatus.CONFIRMED);

		} else if (currentStatus == OrderStatus.CONFIRMED && newStatus == OrderStatus.DELIVERED) {
			order.setStatus(OrderStatus.DELIVERED);

		} else {
			throw new RuntimeException("Invalid order status transition");
		}

		// 3. Save
		orderRepository.save(order);

		// 4. Convert to DTO (reuse your mapper logic)
		return mapToOrderResponse(order);
	}

	private OrderResponse mapToOrderResponse(Order order) {

		List<OrderItemResponse> itemResponses = new ArrayList<>();

		if (order.getOrderItems() != null) {
			for (OrderItem item : order.getOrderItems()) {
				itemResponses.add(new OrderItemResponse(item.getFoodName(), item.getQuantity(), item.getPrice(),
						item.getCreatedAt()));
			}
		}

		return new OrderResponse(order.getId(), order.getTotalAmount(), order.getStatus(), itemResponses,
				order.getCreatedAt());
	}

}
