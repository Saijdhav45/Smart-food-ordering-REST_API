package com.swiggy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swiggy.dto.request.FoodRequestDto;
import com.swiggy.dto.request.RestaurantRequestDto;
import com.swiggy.dto.response.FoodResponseDto;
import com.swiggy.dto.response.RestaurantResponseDto;
import com.swiggy.entity.RestaurantCategory;
import com.swiggy.repository.FoodRepository;
import com.swiggy.service.FoodService;
import com.swiggy.service.RestaurantService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/restaurant")
@Tag(name = "Restaurant API ",description = "Restaurnat API's Endpoints ")
public class RestaurantController {
	
	
	  @Autowired
	    private RestaurantService restaurantService;
	  
	
	  
	  @Autowired
	  private FoodService foodService;
	  
	  @Autowired
	  FoodRepository foodRepository;
	  
	  	@Operation(summary = "Add Restaurant ")
	  	@ApiResponses(value = {
	  			@ApiResponse(responseCode = "201",description = "Restaurant Added Successfully "),@ApiResponse(responseCode = "400",description = "Invalid Data ")
	  	})
	  	
	    @PostMapping("/add")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<RestaurantResponseDto> addRestaurant(
	            @RequestBody RestaurantRequestDto requestDto) {

	        RestaurantResponseDto response = restaurantService.addRestaurant(requestDto);

	        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	    }
	  	
	  	
	  	@Operation(summary = "Get All Restaurant ")
	  	@ApiResponses(value = {
	  			@ApiResponse(responseCode = "200",description = "Fetch All Restaurant Successfully "),@ApiResponse(responseCode = "400",description = "Invalid Data ")})
	  	
	    @GetMapping("/get")
	    public ResponseEntity<Page<RestaurantResponseDto>> getRestaurants(
	            @RequestParam(required = false) String city,
	            @RequestParam(required = false) RestaurantCategory category,
	            @RequestParam(required = false) String search,
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "5") int size,
	            @RequestParam(defaultValue = "name") String sortBy) {

	        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

	        Page<RestaurantResponseDto> response =
	                restaurantService.getRestaurants(city, category, search, pageable);

	        return ResponseEntity.ok(response);
	    }
	  	
	  		  	@Operation(summary = "Get Restaurant by Id ")
	  		  	@ApiResponses(value = {
	  		  			@ApiResponse(responseCode = "200",description = "Restaurant fetch Successfully "),@ApiResponse(responseCode = "400",description = "Restaurant Not FOund ")})
	  		  	
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<RestaurantResponseDto> getRestaurantById(@PathVariable Long id){
	    	 RestaurantResponseDto responseDto= restaurantService.getRestaurantById(id);
	    	 return ResponseEntity.ok(responseDto);
	    }
	  		  	
	  		  		@Operation(summary = "Get restaurant menu")
	  		  	@ApiResponses(value = {
	  		  	        @ApiResponse(responseCode = "200", description = "Menu fetched successfully"),
	  		  	        @ApiResponse(responseCode = "404", description = "Restaurant not found")
	  		  	})
	  		  		
	  		  		  			
	    
	   @GetMapping("/{id}/menu")
	    public ResponseEntity<List<FoodResponseDto>> getRestauratMenu(@PathVariable Long restaurantId){
		  List<FoodResponseDto> menuItems=foodService.viewMenu(restaurantId);
	    	 return ResponseEntity.ok(menuItems);
	    }
	  		  	@Operation(summary = "Add Foods Under Restaurant ")
	  		  @ApiResponses(value = {
	  		          @ApiResponse(responseCode = "200", description = "Food added successfully"),
	  		          @ApiResponse(responseCode = "404", description = "Restaurant not found")
	  		  })
	  		  	
	  		    
	   @PostMapping("/{restaurantId}/foods")
	   @PreAuthorize("hasRole('ADMIN')")
	   public ResponseEntity<FoodResponseDto> addRestaurantTOFood(@PathVariable Long restaurantId,@RequestBody FoodRequestDto request){
		   FoodResponseDto addedFood=foodService.addFoodToRestaurant(restaurantId,request);
		   return ResponseEntity.ok(addedFood);
	   }
	  		  	
	  		  @Operation(summary = "Food Update")
	  		@ApiResponses(value = {
	  		        @ApiResponse(responseCode = "200", description = "Food Updated successfully"),
	  		        @ApiResponse(responseCode = "404", description = "Restaurant not found")
	  		})
	  		
	   @PutMapping("/update/{foodId}/menu")
	   @PreAuthorize("hasRole('ADMIN')")
	   public ResponseEntity<FoodResponseDto> updateFood(
	           @PathVariable Long foodId,
	           @RequestBody FoodRequestDto request) {

	       FoodResponseDto response =
	               foodService.updateFood(foodId, request);

	       return ResponseEntity.ok(response);
	   }
	   @PatchMapping("/{foodId}/availability")
	   @PreAuthorize("hasRole('ADMIN')")
	   public ResponseEntity<FoodResponseDto> toggleAvailability(
	           @PathVariable Long foodId) {

	       FoodResponseDto response =
	               foodService.toggleFoodAvailability(foodId);

	       return ResponseEntity.ok(response);
	   }
	    
}
