package com.pguijaru.cartmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CartManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartManagerApplication.class, args);
	}

}
