package com.swiggy.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.swiggy.dto.request.RestaurantRequestDto;
import com.swiggy.dto.response.RestaurantResponseDto;
import com.swiggy.entity.Restaurant;
@Mapper(componentModel = "spring")
public interface RestaurantMapper {
	// Request DTO → Entity
	
	  @Mapping(target = "id", ignore = true)
	    @Mapping(target = "rating", ignore = true)
	    @Mapping(target= "active", ignore = true)
	    @Mapping(target = "createdAt", ignore = true)
	    @Mapping(target = "foodItems", ignore = true)
    Restaurant toEntity(RestaurantRequestDto dto);

    // Entity → Response DTO
    RestaurantResponseDto toResponse(Restaurant restaurant);
}
