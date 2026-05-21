package com.swiggy.dto.response;

import java.util.List;

public class CartResponse {
	
	private Long cartId;
	private List<CartItemResponse> cartItemResponses;
	private double totalPrice;
	public CartResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartResponse(Long cartId, List<CartItemResponse> cartItemResponses, double totalPrice) {
		super();
		this.cartId = cartId;
		this.cartItemResponses = cartItemResponses;
		this.totalPrice = totalPrice;
	}
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	public List<CartItemResponse> getCartItemResponses() {
		return cartItemResponses;
	}
	public void setCartItemResponses(List<CartItemResponse> cartItemResponses) {
		this.cartItemResponses = cartItemResponses;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
