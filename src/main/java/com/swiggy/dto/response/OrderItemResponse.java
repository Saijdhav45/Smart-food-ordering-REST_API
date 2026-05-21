package com.swiggy.dto.response;

import java.time.LocalDateTime;

public class OrderItemResponse {
	private String foodName;
	private int quantity;
	private double price;
	private LocalDateTime createdAt;
	public OrderItemResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderItemResponse(String foodName, int quantity, double price,LocalDateTime createdAt) {
		super();
		this.foodName = foodName;
		this.quantity = quantity;
		this.price = price;
		this.createdAt=createdAt;
	}
	
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
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
