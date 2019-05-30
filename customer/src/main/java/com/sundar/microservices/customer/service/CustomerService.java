package com.sundar.microservices.customer.service;

import com.sundar.microservices.customer.api.model.Customer;
import com.sundar.microservices.customer.persistence.Schema.CustomerSchema;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    CustomerSchema add(Customer entity);
    CustomerSchema getById(String id);
}
