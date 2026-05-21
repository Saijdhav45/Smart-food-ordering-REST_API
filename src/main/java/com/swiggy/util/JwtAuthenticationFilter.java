package com.swiggy.util;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JWTUtil jwtUtil;
    

   
    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationFilter(JWTUtil jwtUtil,
                                   CustomUserDetailsService customUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }
	

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                   HttpServletResponse response,
                                   FilterChain filterChain)
            throws ServletException, IOException {
    	System.out.println("JWT FILTER HIT");

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7).trim();

        String userEmail = null;

        try {
            userEmail = jwtUtil.extractEmail(token);
            System.out.println("Email from token: " + userEmail);
            
        } catch (Exception e) {
            filterChain.doFilter(request, response);
            return;
        }

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail);

            boolean isValid = jwtUtil.validateToken(token, userDetails.getUsername());
            System.out.println("Token valid: " + isValid);

            if (isValid) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                System.out.println(userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }else {
            	System.out.println("Acccess Denied ");
            }
            System.out.println("Auth set: " + SecurityContextHolder.getContext().getAuthentication());
        }

        filterChain.doFilter(request, response);
        
    }
}
