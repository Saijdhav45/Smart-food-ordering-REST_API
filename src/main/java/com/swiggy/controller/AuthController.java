package com.swiggy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swiggy.dto.request.UserLoginRequest;
import com.swiggy.dto.request.UserRegisterRequest;
import com.swiggy.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(name="Authentication API's",description = "Authentication Management Endpoints")
	
public class AuthController {

    @Autowired
    private UserService userService;
    @Operation(summary = "Register the User ")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200",description = "User Registered Successfully "),@ApiResponse(responseCode = "400",description = "Invalid User Data ")
    })
    @PostMapping("/register")
    public ResponseEntity<String> registerUser( @RequestBody UserRegisterRequest request) {
        
        String response = userService.registerUser(request);
        
        return ResponseEntity.ok(response);
    }
    @Operation(summary = " User Login ")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200",description = "User Login Successfully "),@ApiResponse(responseCode = "400",description = "Bad User Credentials ")
    })
    
    @PostMapping("/login")
    public ResponseEntity<String> registerUser( @RequestBody UserLoginRequest request) {
        
        String response = userService.loginUser(request);
        
        return ResponseEntity.ok(response);
    }
    
    
    // For testing purpose the endpoints for security 
//    
//    @GetMapping("/api/test")
//    public String test() {
//        return "Access Granted";
//    }
//    @GetMapping("/role")
//    public String test3() {
//        return "Role based API HIT ";
//    }
    
   
   
}