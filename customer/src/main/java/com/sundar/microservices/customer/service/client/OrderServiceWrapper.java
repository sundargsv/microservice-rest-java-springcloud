package com.sundar.microservices.customer.service.client;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.sundar.microservices.customer.service.model.request.OrderRequest;
import com.sundar.microservices.customer.service.model.response.OrderResponse;
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
public class OrderServiceWrapper {

    @Value("${service.order-endpoint}")
    protected String ORDER_ENDPOINT;

    private static final String ORDER_API_PATH = "/order";

    @Autowired
    @Qualifier("rest-template")
    private RestTemplate restTemplate;

    public OrderResponse addOrder(String correlationId, OrderRequest entity){

        //0. prepare the request with payload and headers
        HttpEntity<?> requestEntity = new HttpEntity<>( entity, this.buildHeaders() );

        //1. call the order service to create order
        ResponseEntity<OrderResponse> response = restTemplate.exchange( ORDER_ENDPOINT + ORDER_API_PATH + "/" + correlationId,
                HttpMethod.POST,
                requestEntity,
                OrderResponse.class);

        //2. send the response back to the client
        return response.getBody();}

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
