package com.swiggy.service;

import java.util.List;

import com.swiggy.dto.request.PaymentRequest;
import com.swiggy.dto.response.PaymentResponse;

public interface PaymentService {
	
	PaymentResponse initiatePayment(PaymentRequest paymentRequest);

	PaymentResponse markPaymentSuccess(Long paymentId);

	PaymentResponse markPaymentFailed(Long paymentId);

	 PaymentResponse getPaymentById(Long id);

	 List<PaymentResponse> getAllPayments();
}
