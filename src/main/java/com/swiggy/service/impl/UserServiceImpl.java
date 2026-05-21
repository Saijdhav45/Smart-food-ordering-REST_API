package com.swiggy.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.swiggy.dto.request.UserLoginRequest;
import com.swiggy.dto.request.UserRegisterRequest;
import com.swiggy.entity.Role;
import com.swiggy.entity.User;
import com.swiggy.repository.UserRepository;
import com.swiggy.service.UserService;
import com.swiggy.util.JWTUtil;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	JWTUtil jwtUtil;

	@Override
	public String registerUser(UserRegisterRequest request) {
		
		if(userRepository.existsByEmail(request.getEmail())) {
			return "User is already existed ";
		}
		
		
			User user=new User();
		    user.setName(request.getName());
	        user.setEmail(request.getEmail());
	        user.setPassword(passwordEncoder.encode(request.getPassword())); // (we will hash later)
	        user.setContact(request.getContact());
	        user.setAddress(request.getAddress());
		
	        user.setRole(Role.USER);
	        
	        userRepository.save(user);
		
		return "User Registerd Successfully ";
	} 
	
	@Override
	public String loginUser(UserLoginRequest request) {
		Optional<User> userName=userRepository.findByEmail(request.getEmail());
		
		
		
		if(userName.isEmpty()) {
			throw new RuntimeException("Invalid email or password ");
		}
		User user=userName.get();
		
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid email or password ");
		}
		
		return jwtUtil.generateToken(user.getEmail(), user.getRole().name());
	}


}
