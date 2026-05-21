package com.swiggy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.swiggy.dto.request.RestaurantRequestDto;
import com.swiggy.dto.response.RestaurantResponseDto;
import com.swiggy.entity.RestaurantCategory;

public interface RestaurantService {
	public Page<RestaurantResponseDto> getRestaurants(String city,RestaurantCategory category,String search,Pageable pageable);

	public RestaurantResponseDto addRestaurant(RestaurantRequestDto requestDto);
	
	public RestaurantResponseDto getRestaurantById(Long id);
}
