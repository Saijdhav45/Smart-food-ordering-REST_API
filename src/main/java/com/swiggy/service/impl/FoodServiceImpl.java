package com.swiggy.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.swiggy.dto.request.FoodRequestDto;
import com.swiggy.dto.response.FoodResponseDto;
import com.swiggy.entity.Category;
import com.swiggy.entity.FoodItem;
import com.swiggy.entity.Restaurant;
import com.swiggy.exception.FoodNotAvailableException;
import com.swiggy.exception.RestaurantNotFoundException;
import com.swiggy.mapstruct.FoodMapper;
import com.swiggy.repository.CartRepository;
import com.swiggy.repository.FoodRepository;
import com.swiggy.repository.RestaurantRepository;
import com.swiggy.repository.UserRepository;
import com.swiggy.service.FoodService;
import com.swiggy.specification.FoodSpecification;

import jakarta.transaction.Transactional;
@Transactional
@Service
public class FoodServiceImpl implements FoodService{
	@Autowired
	FoodRepository foodRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	@Autowired
	FoodMapper foodMapper;
	
//	@Override
//	public FoodResponseDto createFood(FoodRequestDto request) {
//			
//		Restaurant restaurant=restaurantRepository.findById//(request.getRestaurantId()).orElseThrow(()-> new RestaurantNotFoundException("Restaurant Not Found")) ;
//		
//		FoodItem food=new FoodItem();
//		food.setName(request.getName());
//		food.setDescription(request.getDescription());
//		food.setPrice(request.getPrice());
//		food.setVeg(request.getisVeg());		
//		food.setCategory(request.getCategory());
//		food.setImageUrl(request.getImageUrl());
//		food.setRestaurantId(restaurant);
//		food.setAvailable(true);
//		
//		FoodItem savedFood=foodRepository.save(food);
//		
//		FoodResponseDto response=new FoodResponseDto();
//		
//	    response.setId(savedFood.getId());
//	    response.setName(savedFood.getName());
//	    response.setPrice(savedFood.getPrice());
//	    response.setVeg(savedFood.isVeg());
//	    response.setCategory(savedFood.getCategory());
//	    response.setRestaurantName(savedFood.getRestaurant().getName());
//		
//		return response;
//		
//		
//	}
	
	public FoodResponseDto addFoodToRestaurant(Long restaurantId,FoodRequestDto request) {
		Restaurant IsExistRestaurant=restaurantRepository.findById(restaurantId).orElseThrow(()-> new RestaurantNotFoundException("Restaurant Not exist "));
		
		FoodItem foodItem=foodMapper.toEntity(request);
		foodItem.setRestaurant(IsExistRestaurant);
		
		FoodItem savedFood=foodRepository.save(foodItem);
		
		return foodMapper.toResponse(savedFood);
		
		
		
	}
	@Override
	public FoodResponseDto updateFood(Long foodId, FoodRequestDto request) {

	    FoodItem foodItem = foodRepository.findById(foodId)
	            .orElseThrow(() ->
	                    new RuntimeException("Food Item Not Found"));

	    foodItem.setName(request.getName());
	    foodItem.setDescription(request.getDescription());
	    foodItem.setPrice(request.getPrice());
	    foodItem.setVeg(request.getisVeg());
	    foodItem.setCategory(request.getCategory());
	    foodItem.setImageUrl(request.getImageUrl());

	    FoodItem updatedFood = foodRepository.save(foodItem);

	    return foodMapper.toResponse(updatedFood);
	}
	@Override
	public FoodResponseDto toggleFoodAvailability(Long foodId) {

	    FoodItem foodItem = foodRepository.findById(foodId)
	            .orElseThrow(() ->
	                    new RuntimeException("Food Item Not Found"));

	    foodItem.setAvailable(!foodItem.isAvailable());

	    FoodItem updatedFood = foodRepository.save(foodItem);

	    return foodMapper.toResponse(updatedFood);
	}
	
	@Override
	public Page<FoodResponseDto> getAllFoods(Boolean isVeg,
	        Category category,
	        String keyword,
	        Double minPrice,
	        Double maxPrice,
	        int page,
	        int size,
	        String sortBy,
	        String direction) {

	    Sort sort = direction.equalsIgnoreCase("desc") ?
	            Sort.by(sortBy).descending() :
	            Sort.by(sortBy).ascending();

	    Pageable pageable = PageRequest.of(page, size, sort);

	    // 🔹 Specification (MAIN LOGIC)
	    Specification<FoodItem> spec = Specification
	            .where(FoodSpecification.hasVeg(isVeg))
	            .and(FoodSpecification.hasCategory(category))
	            .and(FoodSpecification.hasKeyword(keyword))
	            .and(FoodSpecification.priceBetween(minPrice, maxPrice));

	    Page<FoodItem> foodPage = foodRepository.findAll(spec, pageable);
	    
	    // 🔹 Convert to DTO
	    return foodPage.map(food -> {
	        FoodResponseDto dto = new FoodResponseDto();
	        dto.setId(food.getId());
	        dto.setName(food.getName());
	        dto.setPrice(food.getPrice());
	        dto.setVeg(food.isVeg());
	        dto.setCategory(food.getCategory());
	        dto.setRestaurantName(
	                food.getRestaurant() != null ? food.getRestaurant().getName() : null
	        );
	        return dto;
	    });
	
	}
	
	@Override
	public FoodResponseDto getFoodById(Long id) {

	    FoodItem food = foodRepository.findById(id)
	            .orElseThrow(() -> new FoodNotAvailableException("Food Not Available"));

	    FoodResponseDto dto = new FoodResponseDto();
	    dto.setId(food.getId());
	    dto.setName(food.getName());
	    dto.setPrice(food.getPrice());
	    dto.setVeg(food.isVeg());
	    dto.setCategory(food.getCategory());
	    dto.setRestaurantName(food.getRestaurant().getName());

	    return dto;
	}
	
	public List<FoodResponseDto> viewMenu(Long id){
		Restaurant restaurant=restaurantRepository.findById(id).orElseThrow(()->  new RestaurantNotFoundException("NOt Found"));
		
		List<FoodItem> items=foodRepository.findByRestaurantAndAvailableTrue(restaurant);
		
		List<FoodResponseDto> menuItems=new ArrayList<>();
		
		
	
	
	for(FoodItem item:items) {
		FoodResponseDto response=new FoodResponseDto();
		response.setId(item.getId());
		response.setName(item.getName());
		response.setPrice(item.getPrice());
		response.setCategory(item.getCategory());
		response.setRestaurantName(item.getRestaurant().getName());
		response.setDescription(item.getDescription());
		response.setAvailable(item.isAvailable());
		response.setVeg(item.isVeg());
		response.setImageUrl(item.getImageUrl());
		
		menuItems.add(response);
		
	}
	return menuItems;
	
	}
	
	
	
}
