package com.swiggy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.swiggy.dto.response.OrderResponse;
import com.swiggy.entity.OrderStatus;

public interface OrderService {
	
	public OrderResponse placeOrder(String email);
	public Page<OrderResponse> getOrdersForUser(String email, OrderStatus orderStatus,Pageable pageable);
	public OrderResponse updateOrderStatus(Long orderId, OrderStatus newStatus);
	
}
