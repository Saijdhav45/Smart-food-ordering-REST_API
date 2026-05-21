package com.swiggy.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="restaurant")
public class Restaurant {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @Column(name = "name", nullable = false)
	    private String name;
	    @Column(name = "address", nullable = false)
	    private String address;
	    @Column(name="phone_number",nullable=false)
	    private String phoneNumber;
	    @Column(name = "city", nullable = false)
	    private String city;
	    @Enumerated(EnumType.STRING)
		@Column(name = "restaurant_category", nullable = false)
	    private RestaurantCategory restaurantCategory;
	    @Column(name = "rating")
	    private double rating=0.0;
	    @Column(name = "is_active", nullable = false)
	    private boolean isActive=true;
	    
	    @Column(name = "created_at", updatable = false)
		private LocalDateTime createdAt;
	    
	    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL,orphanRemoval = true)
	    @JsonIgnore
	    private List<FoodItem> foodItems;

		@PrePersist
		    public void prePersist() {
		        this.createdAt = LocalDateTime.now();
		    }

		public Restaurant() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Restaurant(Long id, String name, String address, String phoneNumber, String city,
				RestaurantCategory restaurantCategory, double rating, boolean isActive, LocalDateTime createdAt,
				List<FoodItem> foodItems) {
			super();
			this.id = id;
			this.name = name;
			this.address = address;
			this.phoneNumber = phoneNumber;
			this.city = city;
			this.restaurantCategory = restaurantCategory;
			this.rating = rating;
			this.isActive = isActive;
			this.createdAt = createdAt;
			this.foodItems = foodItems;
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

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public List<FoodItem> getFoodItems() {
			return foodItems;
		}

		public void setFoodItems(List<FoodItem> foodItems) {
			this.foodItems = foodItems;
		}
		
		

	  
		

		
	    
	    
}
