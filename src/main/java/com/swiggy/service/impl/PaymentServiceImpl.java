package com.swiggy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiggy.dto.request.PaymentRequest;
import com.swiggy.dto.response.PaymentResponse;
import com.swiggy.entity.Order;
import com.swiggy.entity.OrderStatus;
import com.swiggy.entity.Payment;
import com.swiggy.entity.PaymentStatus;
import com.swiggy.exception.PaymentNotFoundException;
import com.swiggy.mapstruct.PaymentMapper;
import com.swiggy.repository.OrderRepository;
import com.swiggy.repository.PaymentRepository;
import com.swiggy.service.PaymentService;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private PaymentMapper paymentMapper;

	@Override
	public PaymentResponse initiatePayment(PaymentRequest paymentRequest) {
		
		Order order=orderRepository.findById(paymentRequest.getOrderId()).orElseThrow(()-> new RuntimeException("Order is missing "));
		
		if(paymentRepository.findByOrderId(order.getId()).isPresent()){
			throw new PaymentNotFoundException("Payment already exist for this order");
		}
		
		Payment payment = new Payment();

		payment.setOrderId(order.getId());

		payment.setAmount(order.getTotalAmount());

		payment.setPaymentMethod(
		        paymentRequest.getPaymentMethod());

		payment.setPaymentStatus(
		        PaymentStatus.PENDING);

		payment.setTransactionId(
		        generateTransactionId());
		
		Payment savedPayment=paymentRepository.save(payment);
		
		
		return paymentMapper.toResponse(savedPayment);
	}

	@Override
	public PaymentResponse markPaymentSuccess(Long paymentId) {

	    Payment payment = paymentRepository.findById(paymentId)
	            .orElseThrow(() ->
	                    new PaymentNotFoundException("Payment not found"));

	    if (payment.getPaymentStatus() != PaymentStatus.PENDING) {
	        throw new PaymentNotFoundException(
	                "Payment already processed with this id ="+paymentId);
	    }

	    payment.setPaymentStatus(PaymentStatus.SUCCESS);

	    paymentRepository.save(payment);

	    Order order = orderRepository
	            .findById(payment.getOrderId())
	            .orElseThrow(() ->
	                    new RuntimeException("Order not found"));

	    order.setStatus(OrderStatus.CONFIRMED);

	    orderRepository.save(order);

	    return paymentMapper.toResponse(payment);
	}
	@Override
	public PaymentResponse markPaymentFailed(Long paymentId) {

	    Payment payment = 	paymentRepository.findById(paymentId)
	            .orElseThrow(() ->
	                    new PaymentNotFoundException("Payment not found with this id = "+paymentId));

	    if (payment.getPaymentStatus() != PaymentStatus.PENDING) {
	        throw new PaymentNotFoundException(	
	                "Payment already processed with this id = "+paymentId);
	    }

	    payment.setPaymentStatus(PaymentStatus.FAILED);

	    paymentRepository.save(payment);

	    Order order = orderRepository
	            .findById(payment.getOrderId())
	            .orElseThrow(() ->
	                    new RuntimeException("Order not found"));

	    order.setStatus(OrderStatus.CANCELLED);

	    orderRepository.save(order);

	    return paymentMapper.toResponse(payment);
	}
	

	@Override
	public PaymentResponse getPaymentById(Long id) {
		Payment isExistPayment=paymentRepository.findById(id).orElseThrow(()-> new PaymentNotFoundException("Payment Not found with this id "+id));
		
		return paymentMapper.toResponse(isExistPayment);
	}

	@Override
	public List<PaymentResponse> getAllPayments() {

	    List<Payment> payments = paymentRepository.findAll();

	    return payments.stream()
	            .map(paymentMapper::toResponse)
	            .toList();
	}
		
	
	
	private String generateTransactionId() {

	    return "TXN" + System.currentTimeMillis();
	}
	
}
