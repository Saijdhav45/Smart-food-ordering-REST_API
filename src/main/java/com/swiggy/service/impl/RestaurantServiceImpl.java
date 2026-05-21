package com.swiggy.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.swiggy.dto.request.RestaurantRequestDto;
import com.swiggy.dto.response.RestaurantResponseDto;
import com.swiggy.entity.Restaurant;
import com.swiggy.entity.RestaurantCategory;
import com.swiggy.exception.RestaurantNotFoundException;
import com.swiggy.mapstruct.RestaurantMapper;
import com.swiggy.repository.RestaurantRepository;
import com.swiggy.service.RestaurantService;
import com.swiggy.specification.RestaurantSpecification;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService{
	@Autowired
	RestaurantRepository restaurantRepository;
	@Autowired
	RestaurantMapper restaurantMapper;
	public RestaurantResponseDto addRestaurant(RestaurantRequestDto requestDto) {

	    Optional<Restaurant> existing =
	            restaurantRepository.findByNameAndCity(
	                    requestDto.getName(),
	                    requestDto.getCity()
	            );

	    if (existing.isPresent()) {
	        throw new RuntimeException("Restaurant already exists in this city");
	    }

	    Restaurant restaurant = restaurantMapper.toEntity(requestDto);

	    Restaurant saved = restaurantRepository.save(restaurant);

	    return restaurantMapper.toResponse(saved);
	}
	
	@Override
	public Page<RestaurantResponseDto> getRestaurants(
	        String city,
	        RestaurantCategory category,
	        String search,
	        Pageable pageable) {

	    Specification<Restaurant> spec = Specification
	            .where(RestaurantSpecification.isActive())
	            .and(RestaurantSpecification.hasCity(city))
	            .and(RestaurantSpecification.hasCategory(category))
	            .and(RestaurantSpecification.hasNameLike(search));

	    Page<Restaurant> restaurantPage = restaurantRepository.findAll(spec, pageable);

	    return restaurantPage.map(restaurantMapper::toResponse);
	}
	
	@Override
	 public RestaurantResponseDto getRestaurantById(Long id) {
		Restaurant restaurant=restaurantRepository.findById(id).orElseThrow(()-> new RestaurantNotFoundException("Restaurant Not Found "));
		
		return restaurantMapper.toResponse(restaurant);
	}
	
	
	
}
