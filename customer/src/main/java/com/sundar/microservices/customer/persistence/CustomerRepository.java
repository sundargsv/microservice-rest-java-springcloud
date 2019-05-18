package com.sundar.microservices.customer.persistence;

import com.sundar.microservices.customer.api.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    // allows insert, get by id etc.
}
