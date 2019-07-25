package com.sundar.microservices.order.service;

import com.sundar.microservices.order.api.model.Order;
import com.sundar.microservices.order.exception.NotFound;
import com.sundar.microservices.order.persistence.OrderRepository;
import com.sundar.microservices.order.persistence.Schema.OrderSchema;
import com.sundar.microservices.order.persistence.util.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    // TODO: 7/25/19 if correlationId is not given, then use UUID
    @Override
    public OrderSchema add(String correlationId, Order entity) {

        log.info("Adding new order for a given correlation id {}", correlationId);
        return orderRepository.save(OrderMapper.toOrderSchema(correlationId, entity));
    }

    @Override
    public OrderSchema load(String id) {

        log.info("Fetching a order entity for a given id {}", id);

        Optional<OrderSchema> entity = orderRepository.findById(id);

        if (entity.isPresent()) return entity.get();
        throw new NotFound(String.format("The given order id %s is not found", id));
    }

    @Override
    public List<OrderSchema> loadByCorrelationId(String correlationId) {

        log.info("Fetching a orders for a given correlationId {}", correlationId);
        return orderRepository.findAllByCorrelationId(correlationId);
    }
}
