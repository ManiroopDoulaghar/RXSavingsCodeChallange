package com.pharmacy.location;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocationApplication {

	public static void main(String[] args) {

		System.out.println(System.getProperty("java.io.tmpdir"));


		SpringApplication.run(LocationApplication.class, args);
	}

}
