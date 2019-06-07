package com.sundar.microservices.customer.service;

import com.sundar.microservices.customer.api.model.Order;
import com.sundar.microservices.customer.exception.NotFound;
import com.sundar.microservices.customer.persistence.OrderRepository;
import com.sundar.microservices.customer.persistence.Schema.OrderSchema;
import com.sundar.microservices.customer.persistence.util.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderSchema add(String customerId, Order entity) {

        log.info("Adding new order for a given customer id {}", customerId);
        return orderRepository.save(OrderMapper.toOrderSchema(customerId, entity));
    }

    @Override
    public OrderSchema load(String id) {

        log.info("Fetching a order entity for a given id {}", id);

        Optional<OrderSchema> entity = orderRepository.findById(id);

        if (entity.isPresent()) return entity.get();
        throw new NotFound(String.format("The given order id %s is not found", id));
    }
}
