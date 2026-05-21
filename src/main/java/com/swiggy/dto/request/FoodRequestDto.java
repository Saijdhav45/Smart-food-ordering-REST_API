package com.swiggy.dto.request;

import com.swiggy.entity.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class FoodRequestDto {
	@NotBlank(message="Food Name is Required ")
	private String name;
	@NotBlank(message="Food description is Required ")
	private String description;
	@Positive(message="Food price  is Required ")
	private double price;
	
	private boolean isVeg;
	@NotNull(message="Food Cateory  is Required ")
	private Category category;
	
	@NotBlank(message = "Image URL is required")
	private String imageUrl;
	
	
	
	public FoodRequestDto() {
		super();
		
	}


	public FoodRequestDto(String name, String description, double price, boolean isVeg, Category category,
			 String imageUrl) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.isVeg = isVeg;
		this.category = category;
		this.imageUrl = imageUrl;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public boolean getisVeg() {
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


	


	


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	
	
}
