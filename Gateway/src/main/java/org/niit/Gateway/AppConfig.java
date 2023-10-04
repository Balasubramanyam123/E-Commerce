package org.niit.Gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    RouteLocator getRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p.path("/api/v1/auth-app/**").uri("http://localhost:8699/*"))
                .route(p-> p.path("/api/v1/vehicle-app/**").uri("http://localhost:8799/*"))
                .build();
    }
}
