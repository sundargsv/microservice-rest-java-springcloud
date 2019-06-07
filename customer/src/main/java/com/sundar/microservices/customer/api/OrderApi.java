package com.sundar.microservices.customer.api;

import com.sundar.microservices.customer.common.Constants;
import com.sundar.microservices.customer.persistence.Schema.OrderSchema;
import com.sundar.microservices.customer.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = Constants.API_ORDER_PATH)
@Slf4j
public class OrderApi {

    @Autowired
    private OrderService orderService;

    @GetMapping(path = "{orderId}")
    public OrderSchema getByOrderId(@PathVariable("orderId") String id){

        return orderService.load(id);
    }
}
