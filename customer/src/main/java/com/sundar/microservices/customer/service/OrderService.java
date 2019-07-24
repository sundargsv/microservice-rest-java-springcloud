package com.sundar.microservices.customer.service;

import com.sundar.microservices.customer.service.model.request.OrderRequest;
import com.sundar.microservices.customer.service.model.response.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    OrderResponse add(String customerId, OrderRequest entity);
    List<OrderResponse> load(String id);
}
