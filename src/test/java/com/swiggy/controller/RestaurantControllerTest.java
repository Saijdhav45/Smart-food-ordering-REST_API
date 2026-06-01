package com.swiggy.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.swiggy.dto.response.RestaurantResponseDto;
import com.swiggy.repository.FoodRepository;
import com.swiggy.service.FoodService;
import com.swiggy.service.RestaurantService;

@WebMvcTest(
	    controllers = RestaurantController.class,
	    excludeAutoConfiguration = SecurityAutoConfiguration.class
	)
	@AutoConfigureMockMvc(addFilters = false)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RestaurantService restaurantService;
    
    @MockitoBean
    private FoodService foodService;
    @MockitoBean
    private FoodRepository foodRepository;

    @Test
    public void testGetRestaurantById() throws Exception {

        RestaurantResponseDto dto = new RestaurantResponseDto();
        dto.setId(1L);
        dto.setName("Dominos");
        dto.setCity("Pune");

        when(restaurantService.getRestaurantById(1L))
                .thenReturn(dto);

        mockMvc.perform(get("/api/restaurants/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Dominos"))
                .andExpect(jsonPath("$.city").value("Pune"));
    }
}