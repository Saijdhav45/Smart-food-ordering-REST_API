package com.swiggy.dto.request;

import jakarta.validation.constraints.Min;

public class UpdateCartItemRequest {
	@Min(0)
	private int quantity;

	public UpdateCartItemRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateCartItemRequest(int quantity) {
		super();
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
