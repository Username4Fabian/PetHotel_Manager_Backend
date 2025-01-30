package com.username4fabian.pethotel_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PethotelManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PethotelManagerApplication.class, args);
		System.out.println("Deployed to: http://localhost:8080");
	}

}
