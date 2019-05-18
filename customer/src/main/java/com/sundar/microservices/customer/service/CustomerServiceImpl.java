package com.sundar.microservices.customer.service;

import com.sundar.microservices.customer.api.model.Customer;
import com.sundar.microservices.customer.exception.NotFound;
import com.sundar.microservices.customer.persistence.CustomerRepository;
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
    public Customer add(Customer entity) {

        log.info("Creating new customer {}", entity.toString());
        return customerRepository.save(entity);
    }

    @Override
    public Customer getById(String id) {

        Optional<Customer> entity = customerRepository.findById(id);

        if (entity.isPresent()) return entity.get();
        throw new NotFound(String.format("The given customer id %s is not found", id));
    }
}
