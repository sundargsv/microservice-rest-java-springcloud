package com.sundar.microservices.customer.service;

import com.sundar.microservices.customer.service.client.OrderServiceWrapper;
import com.sundar.microservices.customer.service.model.request.OrderRequest;
import com.sundar.microservices.customer.service.model.response.OrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderServiceWrapper orderServiceWrapper;


    // TODO: 7/18/19 add how to throw error like NotFound, Badequest, etc...,
    @Override
    public OrderResponse add(String customerId, OrderRequest entity) {

        log.info("Adding new order for a given customer id {}", customerId);

        //0. check if the customer exists - throw err if not

        //1. prepare the request
        return orderServiceWrapper.addOrder(customerId, entity);

    }

    // TODO: 7/18/19 add how to throw error like NotFound, Badequest, etc...,
    @Override
    public List<OrderResponse> load(String customerId) {

        log.info("Fetching a order entity for a given customer id {}", customerId);

        return orderServiceWrapper.getOrdersByCorrelationId(customerId);

    }

    /**
     * Helper functions
     * */

}
