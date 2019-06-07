package com.sundar.microservices.customer.service;

import com.sundar.microservices.customer.api.model.Order;
import com.sundar.microservices.customer.persistence.Schema.OrderSchema;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    OrderSchema add(String customerId, Order entity);
    OrderSchema load(String id);
}
