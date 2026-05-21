package com.swiggy.dto.response;

import com.swiggy.entity.Category;

public class FoodResponseDto {
	private Long id;
	private String name;
	private double price;
	private boolean isVeg;
	private Category category;
	private String restaurantName;
	private String description;
	private boolean available;
	private String imageUrl;
	public FoodResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FoodResponseDto(Long id, String name, double price, boolean isVeg, Category category, String restaurantName,
			String description, boolean available, String imageUrl) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.isVeg = isVeg;
		this.category = category;
		this.restaurantName = restaurantName;
		this.description = description;
		this.available = available;
		this.imageUrl = imageUrl;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isVeg() {
		return isVeg;
	}
	public void setVeg(boolean isVeg) {
		this.isVeg = isVeg;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	
	
}
