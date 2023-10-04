package org.niit.userauthenticationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class UserAuthenticationAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthenticationAppApplication.class, args);
	}


}
