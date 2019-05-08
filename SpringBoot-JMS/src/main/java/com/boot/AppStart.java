package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startup Class
 *
 */
@SpringBootApplication
public class AppStart {
	
	public static void main(String[] args) {
		// Invoking Spring Container
		SpringApplication.run(AppStart.class, args);
	}
}
