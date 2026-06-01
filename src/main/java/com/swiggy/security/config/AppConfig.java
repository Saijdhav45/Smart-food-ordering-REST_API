package com.swiggy.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.swiggy.util.JwtAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class AppConfig {
	
	
	 private JwtAuthenticationFilter jwtAuthenticationFilter;
	 
	public AppConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
		super();
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}
	@Bean
	 PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		}
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	    return config.getAuthenticationManager();
	}
	
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable())
	        .sessionManagement(session ->
	            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        )
	        .authorizeHttpRequests(auth -> auth
	        		// Swagger URLs
                    .requestMatchers(
                    		"/v3/api-docs/**",
                            "/swagger-ui/**",
                            "/swagger-ui.html"
                    ).permitAll()

	            // Public APIs
	            .requestMatchers("/api/auth/**").permitAll()

	            // Role-based APIs
	            .requestMatchers("/api/admin/**").hasRole("ADMIN")
	            .requestMatchers("/api/user/**").hasRole("USER")
	            
	            //FOod Api
	            .requestMatchers("/api/foods/**").permitAll()
	            
	            //Cart Api
	            .requestMatchers("/api/cart/**").permitAll()
	            
	            //Payment API
	            .requestMatchers("/api/payments/**").permitAll()
	            
	            //Restaurant API
	                      .requestMatchers("/api/restaurant/**").permitAll()

	               // Order Api
	                      .requestMatchers("/api/order/**").permitAll()
	                      
	                //Payment Api
	                      .requestMatchers("/api/payments/**").permitAll()
	                      
	            // All others
	            .anyRequest().authenticated()
	        )
	        .addFilterBefore(jwtAuthenticationFilter,
	                UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
}
