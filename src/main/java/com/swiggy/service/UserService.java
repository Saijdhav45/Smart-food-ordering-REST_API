package com.swiggy.service;

import com.swiggy.dto.request.UserLoginRequest;
import com.swiggy.dto.request.UserRegisterRequest;


public interface UserService {
	
	public String registerUser(UserRegisterRequest request);
	public String loginUser(UserLoginRequest request);
}
