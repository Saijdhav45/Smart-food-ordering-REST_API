package com.swiggy.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.swiggy.dto.request.FoodRequestDto;
import com.swiggy.dto.response.FoodResponseDto;
import com.swiggy.dto.response.RestaurantResponseDto;
import com.swiggy.entity.Category;
import com.swiggy.entity.FoodItem;
import com.swiggy.entity.Restaurant;
import com.swiggy.exception.RestaurantNotFoundException;
import com.swiggy.mapstruct.FoodMapper;
import com.swiggy.mapstruct.RestaurantMapper;
import com.swiggy.repository.FoodRepository;
import com.swiggy.repository.RestaurantRepository;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceImplTest {
	@Mock
	private RestaurantRepository repository;
	@Mock
	private RestaurantMapper restaurantMapper;
	@Mock
	private FoodRepository foodRepository;
	@Mock
	private FoodMapper foodMapper;
	@InjectMocks
	private RestaurantServiceImpl restaurantService;
    @InjectMocks
	private FoodServiceImpl foodService;
	
	
    @Test
    public void testSaveFoodUnderRestaurant() {

        // Request DTO
        FoodRequestDto request = new FoodRequestDto();
        request.setName("Pizza");
        request.setDescription("Cheese Pizza");
        request.setPrice(150);
        request.setVeg(true);
        request.setCategory(Category.STARTER);

        // Restaurant Entity
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Dominos");
        restaurant.setCity("Pune");

        // Food Entity
        FoodItem foodItem = new FoodItem();
        foodItem.setName("Pizza");
        foodItem.setDescription("Cheese Pizza");
        foodItem.setPrice(150);
        foodItem.setVeg(true);
        foodItem.setCategory(Category.STARTER);

        // Saved Food Entity
        FoodItem savedFoodItem = new FoodItem();
        savedFoodItem.setId(10L);
        savedFoodItem.setName("Pizza");
        savedFoodItem.setDescription("Cheese Pizza");
        savedFoodItem.setPrice(150);
        savedFoodItem.setVeg(true);
        savedFoodItem.setCategory(Category.STARTER);
        savedFoodItem.setRestaurant(restaurant);

        // Response DTO
        FoodResponseDto responseDto = new FoodResponseDto();
        responseDto.setId(10L);
        responseDto.setName("Pizza");
        responseDto.setPrice(150);
        responseDto.setVeg(true);
        responseDto.setCategory(Category.STARTER);
        responseDto.setRestaurantName("Dominos");

        // Mock Repository and Mapper Behavior

        when(repository.findById(1L))
                .thenReturn(Optional.of(restaurant));

        when(foodMapper.toEntity(request))
                .thenReturn(foodItem);

        when(foodRepository.save(foodItem))
                .thenReturn(savedFoodItem);

        when(foodMapper.toResponse(savedFoodItem))
                .thenReturn(responseDto);

        // Act
        FoodResponseDto result =
                foodService.addFoodToRestaurant(1L, request);

        
        assertNotNull(result);
        assertEquals("Pizza", result.getName());
        assertEquals(150, result.getPrice());
        assertEquals("Dominos", result.getRestaurantName());

       
        verify(repository).findById(1L);
        verify(foodMapper).toEntity(request);
        verify(foodRepository).save(foodItem);
        verify(foodMapper).toResponse(savedFoodItem);
    }
    
    @Test
    public void shouldThrowExceptionWhenRestaurantNotExist(){
    	when(repository.findById(1L))
        .thenReturn(Optional.empty());
    	assertThrows(
	            RestaurantNotFoundException.class,
	            () -> restaurantService.getRestaurantById(1L)
	    );

	    verify(repository).findById(1L);
    	
    }
	
	
	@Test
	public void testGetRestaurantById_Success() {
		Restaurant restaurant=new Restaurant();
		restaurant.setId(1L);
		restaurant.setName("Dominos");
		restaurant.setCity("Pune");
		
		RestaurantResponseDto dto =
		        new RestaurantResponseDto();
		dto.setId(1L);
		dto.setName("Dominos");
		dto.setCity("Pune");
		
		when(repository.findById(1L))
        .thenReturn(Optional.of(restaurant));
		
		when(restaurantMapper.toResponse(restaurant))
        .thenReturn(dto);
		RestaurantResponseDto result =restaurantService.getRestaurantById(1l);
		assertNotNull(result);
		assertEquals(1L, result.getId());
		assertEquals("Dominos", result.getName());
		assertEquals("Pune", result.getCity());
		verify(repository).findById(1L);
		verify(restaurantMapper).toResponse(restaurant);
	}
	
	@Test
	public void shouldThrowExceptionWhenRestaurantNotFound() {

	    when(repository.findById(1L))
	            .thenReturn(Optional.empty());

	    assertThrows(
	            RestaurantNotFoundException.class,
	            () -> restaurantService.getRestaurantById(1L)
	    );

	    verify(repository).findById(1L);
	}
}
