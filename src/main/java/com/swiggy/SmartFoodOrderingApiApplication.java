package com.swiggy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Smart Restaurant Food API ",version = "1.0",description = "It "))
public class SmartFoodOrderingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartFoodOrderingApiApplication.class, args);
	}

}
