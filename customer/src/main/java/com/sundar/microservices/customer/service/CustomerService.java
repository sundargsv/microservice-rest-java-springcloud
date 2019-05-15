package com.sundar.microservices.customer.service;

import com.sundar.microservices.customer.api.model.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    Customer add(Customer entity);
    Customer getById(String id);
}
