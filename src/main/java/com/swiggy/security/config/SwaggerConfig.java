package com.swiggy.security.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Smart Food Ordering REST API",
                version = "v1.0",
                description = "Backend REST API for food ordering platform with authentication, restaurant, food, cart, and order management."
        ),
        servers = {
                @Server(
                        url = "http://localhost:8084",
                        description = "Local Development Server"
                )
        }
)
public class SwaggerConfig {

}