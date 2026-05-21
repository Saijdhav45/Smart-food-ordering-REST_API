package com.swiggy.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.swiggy.entity.OrderStatus;

public class OrderResponse {
	
	private Long orderId;
	private double totalAmount;
	private OrderStatus status;
	private List<OrderItemResponse> items;
	private LocalDateTime createdAt;
	public OrderResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderResponse(Long orderId, double totalAmount, OrderStatus status, List<OrderItemResponse> items,LocalDateTime createdAt) {
		super();
		this.orderId = orderId;
		this.totalAmount = totalAmount;
		this.status = status;
		this.items = items;
		this.createdAt=createdAt;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public List<OrderItemResponse> getItems() {
		return items;
	}
	public void setItems(List<OrderItemResponse> items) {
		this.items = items;
	}
	
	
	
	
	
}
