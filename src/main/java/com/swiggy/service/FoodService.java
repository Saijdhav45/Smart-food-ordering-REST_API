package com.swiggy.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.swiggy.dto.request.FoodRequestDto;
import com.swiggy.dto.response.FoodResponseDto;
import com.swiggy.entity.Category;

public interface FoodService {
	
	FoodResponseDto addFoodToRestaurant(Long restaurantId,  FoodRequestDto request);
	
	Page<FoodResponseDto> getAllFoods(Boolean isVeg,
	        Category category,
	        String keyword,
	        Double minPrice,
	        Double maxPrice,
	        int page,
	        int size,
	        String sortBy,
	        String direction);
	
	FoodResponseDto getFoodById(Long id);
	
	List<FoodResponseDto> viewMenu(Long id);
	FoodResponseDto updateFood(Long foodId, FoodRequestDto request);
	FoodResponseDto toggleFoodAvailability(Long foodId);
	
	
}
