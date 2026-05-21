package com.swiggy.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ErrorResponse buildError(String message, String description, String error) {
        return new ErrorResponse(
                LocalDateTime.now(),
                message,
                description,
                error
        );
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRestaurantNotFound(
            RestaurantNotFoundException ex,
            WebRequest request) {

        return new ResponseEntity<>(
                buildError(ex.getMessage(), request.getDescription(false), "Restaurant Not Found"),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(FoodNotAvailableException.class)
    public ResponseEntity<ErrorResponse> handleFoodNotFound(
            FoodNotAvailableException ex,
            WebRequest request) {

        return new ResponseEntity<>(
                buildError(ex.getMessage(), request.getDescription(false), "Food Not Found"),
                HttpStatus.NOT_FOUND
        );
    }

    //  VERY IMPORTANT (fallback handler)
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex,
            WebRequest request) {

        return new ResponseEntity<>(
                buildError(ex.getMessage(), request.getDescription(false), "Internal Server Error"),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
    

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            WebRequest request) {

        String errorMsg = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        return new ResponseEntity<>(
                buildError(errorMsg, request.getDescription(false), "Validation Failed"),
                HttpStatus.BAD_REQUEST
        );
    }
}