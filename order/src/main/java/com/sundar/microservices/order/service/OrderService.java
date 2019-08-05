package com.sundar.microservices.order.service;

import com.sundar.microservices.order.api.model.Order;
import com.sundar.microservices.order.persistence.Schema.OrderSchema;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    OrderSchema add(String correlationId, Order entity);
    OrderSchema load(String id);
    List<OrderSchema> loadByCorrelationId(String correlationId);
}
