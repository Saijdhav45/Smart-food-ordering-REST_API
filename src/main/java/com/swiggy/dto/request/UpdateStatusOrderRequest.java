package com.swiggy.dto.request;

import com.swiggy.entity.OrderStatus;

public class UpdateStatusOrderRequest {
	private OrderStatus orderStatus;

	public UpdateStatusOrderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateStatusOrderRequest(OrderStatus orderStatus) {
		super();
		this.orderStatus = orderStatus;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
}
