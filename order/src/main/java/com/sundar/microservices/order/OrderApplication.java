package com.sundar.microservices.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Date;

@EnableEurekaClient
@SpringBootApplication
@EnableMongoRepositories
@Slf4j
public class OrderApplication {

	public static void main(String[] args) {

		SpringApplication.run(OrderApplication.class, args);
		log.info("Order service starting .....{}", new Date());
	}
}
