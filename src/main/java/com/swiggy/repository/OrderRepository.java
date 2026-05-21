package com.swiggy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.swiggy.entity.Order;
import com.swiggy.entity.OrderStatus;
import com.swiggy.entity.User;

public interface OrderRepository extends JpaRepository<Order, Long>{
	Page<Order> findByUser(User user, Pageable pageable);
	
	Page<Order> findByUserAndStatus(User user, OrderStatus status, Pageable pageable);
}
