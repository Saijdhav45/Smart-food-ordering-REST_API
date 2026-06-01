package com.swiggy.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swiggy.dto.request.UpdateStatusOrderRequest;
import com.swiggy.dto.response.OrderResponse;
import com.swiggy.entity.OrderStatus;
import com.swiggy.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	@PostMapping
	public ResponseEntity<OrderResponse> placeOrder(Principal principal) {

	    String email = principal.getName();
	    return ResponseEntity.ok(orderService.placeOrder(email));
	}
	
	@GetMapping
	public ResponseEntity<Page<OrderResponse>> getOrders(
	        Principal principal,@RequestParam(required =false) OrderStatus orderStatus,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size) {

	    Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

	    return ResponseEntity.ok(
	        orderService.getOrdersForUser(principal.getName(), orderStatus, pageable)
	    );
	}
	
	@PutMapping("/{orderId}/orderStatus")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<OrderResponse> updateStatus(
	        @PathVariable Long orderId,
	        @RequestBody UpdateStatusOrderRequest request) {

	    return ResponseEntity.ok(
	        orderService.updateOrderStatus(orderId, request.getOrderStatus())
	    );
	}
}
