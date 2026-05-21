package com.swiggy.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.swiggy.entity.Restaurant;
import com.swiggy.entity.RestaurantCategory;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>,JpaSpecificationExecutor<Restaurant>{
	Optional<Restaurant> findByName(String name);
	Optional<Restaurant> findByNameAndCity(String name,String city);

    Page<Restaurant> findByRestaurantCategory(
        RestaurantCategory category,
        Pageable pageable
    );

    Page<Restaurant> findByCityAndRestaurantCategory(
        String city,
        RestaurantCategory category,
        Pageable pageable
    );

    Page<Restaurant> findByNameContainingIgnoreCase(
        String name,
        Pageable pageable
    );
    
  
}
