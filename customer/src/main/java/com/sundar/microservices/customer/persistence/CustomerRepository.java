package com.sundar.microservices.customer.persistence;

import com.sundar.microservices.customer.persistence.Schema.CustomerSchema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerSchema, String> {

    // allows insert, get by id etc.
}
