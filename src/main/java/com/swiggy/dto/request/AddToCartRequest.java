package com.swiggy.dto.request;

public class AddToCartRequest {
	
	private Long foodId;
	
	private int quantity;

	public AddToCartRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddToCartRequest(Long foodId, int quantity) {
		super();
		this.foodId = foodId;
		this.quantity = quantity;
	}

	public Long getFoodId() {
		return foodId;
	}

	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
