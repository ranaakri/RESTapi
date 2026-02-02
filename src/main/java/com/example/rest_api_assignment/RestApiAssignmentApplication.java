package com.example.rest_api_assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RestApiAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiAssignmentApplication.class, args);
	}

}
