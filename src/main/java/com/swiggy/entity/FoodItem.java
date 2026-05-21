package com.swiggy.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="food_item")
public class FoodItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name",nullable = false)
	private String name;
	
	@Column(name="description",nullable = false)
	private String description;
	
	@Column(name="price",nullable = false)
	private double price;
	
	
	@Column(name="isVeg",nullable = false)
	private boolean isVeg;
	
	
	   @Enumerated(EnumType.STRING)
	    @Column(name = "category", nullable = false)
	private Category category;
	
	
	@Column(name="imageUrl",nullable = false)
	private String imageUrl;
	
	@ManyToOne
	@JoinColumn(name="restaurant_id",nullable = false)
	private Restaurant restaurant;
	
	@Column(name="available")
	private boolean available;
	
	 @Column(name = "created_at", updatable = false)
	    private LocalDateTime createdAt;

	    @Column(name = "updated_at")
	    private LocalDateTime updatedAt;
	    
	    public FoodItem() {}

		public FoodItem(Long id, String name, String description, double price, boolean isVeg, Category category,
				String imageUrl, Restaurant restaurant, boolean available, LocalDateTime createdAt,
				LocalDateTime updatedAt) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.price = price;
			this.isVeg = isVeg;
			this.category = category;
			this.imageUrl = imageUrl;
			this.restaurant = restaurant;
			this.available = available;
			this.createdAt = createdAt;
			this.updatedAt = updatedAt;
		}
		
		@PrePersist
		public void onCreate() {
		    this.createdAt = LocalDateTime.now();
		}

		@PreUpdate
		public void onUpdate() {
		    this.updatedAt = LocalDateTime.now();
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

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		

		public Restaurant getRestaurant() {
			return restaurant;
		}

		public void setRestaurant(Restaurant restaurant) {
			this.restaurant = restaurant;
		}

		public boolean isAvailable() {
			return available;
		}

		public void setAvailable(boolean available) {
			this.available = available;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public LocalDateTime getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}
		
		
	    
	    
	
}
