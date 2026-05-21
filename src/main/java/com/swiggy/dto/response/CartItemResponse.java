package com.swiggy.dto.response;

public class CartItemResponse {
	private Long foodId;
	private String foodName;
	private int quantity;
	private double price;
	public CartItemResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartItemResponse(Long foodId, String foodName, int quantity, double price) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.quantity = quantity;
		this.price = price;
	}
	public Long getFoodId() {
		return foodId;
	}
	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
