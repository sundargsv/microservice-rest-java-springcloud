package com.sundar.microservices.customer.service;

import com.sundar.microservices.customer.api.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("customer-service")
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    @Override
    public Customer add(Customer entity) {

        log.info("Creating new customer {}", entity.toString());
        return entity;
    }

    @Override
    public Customer getById(String id) {

        return !id.equals("0") ? Customer.builder()
                .id(id)
                .firstName("Test")
                .lastName("Test")
                .email("Test")
                .phoneNumber("987654321")
                .build() : Customer.builder().build();
    }
}
