package com.swiggy.exception;

public class FoodNotAvailableException extends RuntimeException{
	public FoodNotAvailableException(String message) {
		super(message);
	}
}
