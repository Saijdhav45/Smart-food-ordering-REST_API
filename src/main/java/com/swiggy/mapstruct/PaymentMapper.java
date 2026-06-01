package com.swiggy.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.swiggy.dto.response.PaymentResponse;
import com.swiggy.entity.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
	
	 @Mapping(source = "id", target = "paymentId")
	PaymentResponse toResponse(Payment payment);
	
	 	

	
}
