package com.sundar.microservices.customer.service;

import com.sundar.microservices.customer.api.model.Customer;
import com.sundar.microservices.customer.exception.NotFound;
import com.sundar.microservices.customer.persistence.CustomerRepository;
import com.sundar.microservices.customer.persistence.Schema.CustomerSchema;
import com.sundar.microservices.customer.persistence.util.CustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("customer-service")
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public CustomerSchema add(Customer entity) {

        log.info("Creating new customer {}", entity.toString());
        return customerRepository.save(CustomerMapper.toCustomerSchema(entity));
    }

    @Override
    public CustomerSchema getById(String id) {

        log.info("Fetching a customer entity for a given id {}", id);

        Optional<CustomerSchema> entity = customerRepository.findById(id);

        if (entity.isPresent()) return entity.get();
        throw new NotFound(String.format("The given customer id %s is not found", id));
    }
}
