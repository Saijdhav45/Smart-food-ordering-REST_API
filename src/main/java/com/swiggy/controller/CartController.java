package com.swiggy.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swiggy.dto.request.AddToCartRequest;
import com.swiggy.dto.request.UpdateCartItemRequest;
import com.swiggy.dto.response.CartResponse;
import com.swiggy.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	CartService cartService;

	@PostMapping("/add")
	public ResponseEntity<String> addToCart(@RequestBody AddToCartRequest request, Principal principle) {
		String email = principle.getName();
		return ResponseEntity.ok(cartService.addToCart(request, email));
	}

	@GetMapping
	public ResponseEntity<CartResponse> viewCart(Principal principal) {

		String email = principal.getName();
		return ResponseEntity.ok(cartService.getCartForUser(email));
	}

	@PutMapping("/item/{cartItemId}")
	public ResponseEntity<CartResponse> updateCart(@PathVariable Long cartItemId, Principal principal,
			@Valid @RequestBody UpdateCartItemRequest cartItemRequest) {

		String email = principal.getName();
		int quantity = cartItemRequest.getQuantity();

		return ResponseEntity.ok(cartService.updateCartItem(email, cartItemId, quantity));
	}
	
	@DeleteMapping("/items/{cartItemId}")
	public ResponseEntity<CartResponse> removeItem(
	        @PathVariable Long cartItemId,
	        Principal principal) {

	    String email = principal.getName();
	    return ResponseEntity.ok(cartService.removeCartItem(email, cartItemId));
	}
	
	@DeleteMapping
	public ResponseEntity<CartResponse> clearCart(
	        Principal principal) {

	    String email = principal.getName();
	    return ResponseEntity.ok(cartService.clearCart(email));
	} 
}
