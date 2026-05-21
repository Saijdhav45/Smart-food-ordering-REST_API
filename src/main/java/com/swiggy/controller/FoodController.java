package com.swiggy.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swiggy.dto.response.FoodResponseDto;
import com.swiggy.entity.Category;
import com.swiggy.repository.CartRepository;
import com.swiggy.service.CartService;
import com.swiggy.service.FoodService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/food")
@Tag(name="Food API's ",description = "Food Management API's Endpoints ")
public class FoodController {

	
	@Autowired
	FoodService foodService;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CartService cartService;
	
//	@PostMapping("/foods")
//	public ResponseEntity<FoodResponseDto> create(@Valid @RequestBody FoodRequestDto request){
//		
//		return  //ResponseEntity.ok(foodService.createFood(request));
//	}
	
	
	
	@Operation(summary = "Get all foods ,The default max size is 5 ")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "All Foods fetched Successfully "),
			@ApiResponse(responseCode = "404",description = "Food not Available ")
	})
	@GetMapping("/foods")
	public ResponseEntity<Page<FoodResponseDto>> getAllFoods(

	        @RequestParam(required = false) Boolean isVeg,
	        @RequestParam(required = false) Category category,
	        @RequestParam(required = false) String keyword,
	        @RequestParam(required = false) Double minPrice,
	        @RequestParam(required = false) Double maxPrice,

	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size,

	        @RequestParam(defaultValue = "price") String sortBy,
	        @RequestParam(defaultValue = "asc") String direction

	) {

	    return ResponseEntity.ok(
	            foodService.getAllFoods(
	                    isVeg,
	                    category,
	                    keyword,
	                    minPrice,
	                    maxPrice,
	                    page,
	                    size,
	                    sortBy,
	                    direction
	            )
	    );
	}

	@Operation(summary = "Get specific food by Id ,The default max size is 5 ")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = " Food fetched Successfully "),
			@ApiResponse(responseCode = "404",description = "Food not Available ")
	})

    @GetMapping("/{id}")
    public ResponseEntity<FoodResponseDto> foodById(@PathVariable Long id){
    	return ResponseEntity.ok(foodService.getFoodById(id));
    }
    
    
    
	
	
}
