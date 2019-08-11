package com.sundar.microservices.customer.service.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderServiceWrapper {

    @Value("${service.order-endpoint}")
    protected String ORDER_ENDPOINT;

    private static final String ORDER_API_PATH = "/order";

    @Autowired
    @Qualifier("rest-template")
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "addOrder_fallback", commandKey = "order_add", groupKey = "order_client")
    public OrderResponse addOrder(String correlationId, OrderRequest entity){

        //0. prepare the request with payload and headers
        HttpEntity<?> requestEntity = new HttpEntity<>( entity, this.buildHeaders() );

        //1. call the order service to create order
        ResponseEntity<OrderResponse> response = restTemplate.exchange( ORDER_ENDPOINT + ORDER_API_PATH + "/" + correlationId,
                HttpMethod.POST,
                requestEntity,
                OrderResponse.class);

        //2. send the response back to the client
        return response.getBody();
    }

    @HystrixCommand(fallbackMethod = "getOrdersByCorrelationId_fallback", commandKey = "orders_get", groupKey = "order_client")
    public List<OrderResponse> getOrdersByCorrelationId(String correlationId){

        //0. prepare the request with headers
        HttpEntity<?> requestEntity = new HttpEntity<>( this.buildHeaders() );

        //1. call the order service to create order
        ResponseEntity<List<OrderResponse>> response = restTemplate.exchange(ORDER_ENDPOINT + ORDER_API_PATH + "/?correlationId=" + correlationId,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<OrderResponse>>(){});

        //2. send the response back to the client
        return response.getBody();
    }

    /**
     * Fallback methods here
     * */

    // TODO: 8/10/19 Use this fallback to list down the orders from cache - Redis cluster
    private List<OrderResponse> getOrdersByCorrelationId_fallback(String correlationId){

        log.info("Trying to fetch orders from cache for a given correlationId {}", correlationId);

        //Stream the orders from caching mechanism - Redis cluster
        return new ArrayList<>();
    }

    // TODO: 8/10/19 Use this fallback to add order as an event to kafka, assuming the order service will pick it up once its up and running
    private OrderResponse addOrder_fallback(String correlationId, OrderRequest entity){

        //Send order event to Event-Driven/ Queue mechanism - Kafka cluster
        return OrderResponse.builder().build();
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
