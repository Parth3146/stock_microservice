package com.example.g3s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class StockMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockMicroserviceApplication.class, args);
	}

}
