package com.swiggy.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swiggy.dto.request.FoodRequestDto;

@RestController
@RequestMapping("/api/admin/foods")
public class AdminFoodController {
	
	@PostMapping("/addFood")
	public String addFood(@RequestBody FoodRequestDto foodRequest) {
		return "null:";
	}
	
	@PutMapping("/edit")
	public String update(@RequestBody FoodRequestDto foodRequest) {
		return "Food details updated";
	}
	
}
