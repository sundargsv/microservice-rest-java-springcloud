package com.sundar.microservices.customer.service;

import com.sundar.microservices.customer.service.model.request.OrderRequest;
import com.sundar.microservices.customer.service.model.response.OrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Value("${order.service.endpoint}")
    protected String orderServiceEndpoint;

    private static final String ORDER_API_PATH = "/order";

    @Autowired
    @Qualifier("rest-template")
    private RestTemplate restTemplate;

    // TODO: 7/18/19 add how to throw error like NotFound, Badequest, etc...,
    @Override
    public OrderResponse add(String customerId, OrderRequest entity) {

        log.info("Adding new order for a given customer id {}", customerId);

        //0. check if the customer exists - throw err if not

        //1. prepare the request
        HttpEntity<?> requestEntity = new HttpEntity<>( entity, this.buildHeaders() );

        //2. call the order service to create order
        ResponseEntity<OrderResponse> response = restTemplate.exchange(orderServiceEndpoint + ORDER_API_PATH + "/" + customerId,
                HttpMethod.POST,
                requestEntity,
                OrderResponse.class);

        //3. send the response back to the client
        return response.getBody();
    }

    // TODO: 7/18/19 add how to throw error like NotFound, Badequest, etc...,
    @Override
    public List<OrderResponse> load(String customerId) {

        log.info("Fetching a order entity for a given customer id {}", customerId);

        HttpEntity<?> requestEntity = new HttpEntity<>( this.buildHeaders() );

        ResponseEntity<List<OrderResponse>> response = restTemplate.exchange(orderServiceEndpoint + ORDER_API_PATH + "/?customerId=" + customerId,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<OrderResponse>>(){});

        return response.getBody();
    }

    /**
     * Helper functions
     * */
    private HttpHeaders buildHeaders(){

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");

        return headers;
    }
}
