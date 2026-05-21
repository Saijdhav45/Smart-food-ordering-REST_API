package com.swiggy.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.swiggy.entity.Category;
import com.swiggy.entity.FoodItem;
import com.swiggy.entity.Restaurant;

public interface FoodRepository extends JpaRepository<FoodItem, Long>,JpaSpecificationExecutor<FoodItem>{
	
	Page<FoodItem> findByIsVeg(boolean isVeg, Pageable pageable);

	Page<FoodItem> findByCategory(Category category, Pageable pageable);

	Page<FoodItem> findByIsVegAndCategory(boolean isVeg, Category category, Pageable pageable);
	
	Page<FoodItem> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
	        String name,
	        String description,
	        Pageable pageable
	);
	List<FoodItem> findByRestaurantAndAvailableTrue(Restaurant restaurant);
	  
	
	
}
