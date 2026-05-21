package com.swiggy.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.swiggy.dto.request.FoodRequestDto;
import com.swiggy.dto.response.FoodResponseDto;
import com.swiggy.entity.FoodItem;

@Mapper(componentModel = "spring")
public interface FoodMapper {

	
	FoodItem toEntity(FoodRequestDto request);
	@Mapping(source = "restaurant.name", target = "restaurantName")
	FoodResponseDto toResponse(FoodItem foodItem);
}
