package com.swiggy.dto.request;

import com.swiggy.entity.RestaurantCategory;

public class RestaurantRequestDto {
	private String name;
	private String address;
	private String phoneNumber;
	private String city;
	private RestaurantCategory restaurantCategory;
	public RestaurantRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RestaurantRequestDto(String name, String address, String phoneNumber, String city,
			RestaurantCategory restaurantCategory) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.restaurantCategory = restaurantCategory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	
	
}
