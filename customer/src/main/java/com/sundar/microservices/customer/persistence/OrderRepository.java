package com.sundar.microservices.customer.persistence;

import com.sundar.microservices.customer.persistence.Schema.OrderSchema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<OrderSchema, String> {
}
