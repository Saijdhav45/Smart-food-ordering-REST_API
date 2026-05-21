package com.swiggy.dto.response;

import com.swiggy.entity.RestaurantCategory;

public class RestaurantResponseDto {
	
	
	private Long id;
	private String name;
	private String city;
	private RestaurantCategory restaurantCategory;
	private double rating;
	private boolean isActive;
	public RestaurantResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RestaurantResponseDto(Long id, String name, String city, RestaurantCategory restaurantCategory,
			double rating, boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.restaurantCategory = restaurantCategory;
		this.rating = rating;
		this.isActive = isActive;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public RestaurantCategory getRestaurantCategory() {
		return restaurantCategory;
	}
	public void setRestaurantCategory(RestaurantCategory restaurantCategory) {
		this.restaurantCategory = restaurantCategory;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
