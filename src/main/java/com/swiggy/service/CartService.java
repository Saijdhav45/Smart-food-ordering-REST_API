package com.swiggy.service;

import com.swiggy.dto.request.AddToCartRequest;
import com.swiggy.dto.response.CartResponse;

public interface CartService {
	
	public String addToCart(AddToCartRequest addToCartRequest, String userEmail);
	
	public CartResponse getCartForUser(String email);
	
	public CartResponse updateCartItem(String email, Long cartItemId, int quantity);
	
	public CartResponse removeCartItem(String email, Long cartItemId);
	
	public CartResponse clearCart(String email);
}
