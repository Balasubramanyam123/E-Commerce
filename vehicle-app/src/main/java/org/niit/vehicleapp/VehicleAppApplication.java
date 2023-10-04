package org.niit.vehicleapp;

import org.niit.vehicleapp.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VehicleAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleAppApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean jwtFilter(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.addUrlPatterns("/api/v1/get-user-details","/api/v1/add-product-to-user", "/api/v1/admin/add-new-product","/api/v1/admin/update-product/{vehicleId}","/api/v1/admin/delete-product/{vehicleId}");


		return filterRegistrationBean;
	}
}
