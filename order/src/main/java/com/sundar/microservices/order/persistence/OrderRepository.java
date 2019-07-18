package com.sundar.microservices.order.persistence;

import com.sundar.microservices.order.persistence.Schema.OrderSchema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<OrderSchema, String> {

    List<OrderSchema> findAllByCustomerId(String customerId);
}
