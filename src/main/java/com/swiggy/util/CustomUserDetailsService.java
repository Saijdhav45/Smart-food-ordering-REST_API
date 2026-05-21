package com.swiggy.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.swiggy.entity.User;
import com.swiggy.repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	    Optional<User> user = userRepository.findByEmail(username);

	    User userInfo = user.orElseThrow(() ->
	            new UsernameNotFoundException("Email not found"));

	    return new org.springframework.security.core.userdetails.User(
	            userInfo.getEmail(),
	            userInfo.getPassword(),
	            java.util.List.of(new SimpleGrantedAuthority("ROLE_" + userInfo.getRole().name()))
	    );
	}

}
