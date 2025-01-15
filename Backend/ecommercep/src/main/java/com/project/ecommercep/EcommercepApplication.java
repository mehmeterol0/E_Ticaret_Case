package com.project.ecommercep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class EcommercepApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommercepApplication.class, args);
	}

}
