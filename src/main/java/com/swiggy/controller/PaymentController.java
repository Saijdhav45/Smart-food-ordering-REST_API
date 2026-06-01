package com.swiggy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swiggy.dto.request.PaymentRequest;
import com.swiggy.dto.response.PaymentResponse;
import com.swiggy.service.PaymentService;



@RestController
@RequestMapping("/api/payments")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/initiate")
	public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest) {
		 return ResponseEntity.ok(paymentService.initiatePayment(paymentRequest));
	}
	@PostMapping("/{paymentId}/success")
	public ResponseEntity<PaymentResponse>
	markPaymentSuccess(@PathVariable Long paymentId) {

	    return ResponseEntity.ok(
	            paymentService.markPaymentSuccess(paymentId));
	}
	@PostMapping("/{paymentId}/fail")
	public ResponseEntity<PaymentResponse>
	markPaymentFailed(@PathVariable Long paymentId) {

	    return ResponseEntity.ok(
	            paymentService.markPaymentFailed(paymentId));
	}
	
	@GetMapping("/{paymentId}")
	public ResponseEntity<PaymentResponse> viewPayment(@PathVariable Long paymentId){
		return ResponseEntity.ok(
	            paymentService.getPaymentById(paymentId));
	}
	
	@GetMapping("/view/allPayments")	
	public ResponseEntity<List<PaymentResponse>> getAllPayments() {

	    return ResponseEntity.ok(
	            paymentService.getAllPayments());
	}
	
}
