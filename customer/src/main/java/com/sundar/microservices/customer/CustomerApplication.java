package com.sundar.microservices.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
@Slf4j
public class CustomerApplication {

	public static void main(String[] args) {

		SpringApplication.run(CustomerApplication.class, args);
		log.info("Customer service starting .....{}", new Date());
	}
}
