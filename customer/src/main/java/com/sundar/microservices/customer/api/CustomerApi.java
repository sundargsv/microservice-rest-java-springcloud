package com.sundar.microservices.customer.api;

import com.sundar.microservices.customer.api.model.Customer;
import com.sundar.microservices.customer.api.model.Order;
import com.sundar.microservices.customer.common.Constants;
import com.sundar.microservices.customer.persistence.Schema.CustomerSchema;
import com.sundar.microservices.customer.persistence.Schema.OrderSchema;
import com.sundar.microservices.customer.service.CustomerService;
import com.sundar.microservices.customer.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(path = {Constants.API_CUSTOMER_PATH}, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class CustomerApi {

    @Autowired
    @Qualifier("customer-service")
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    /*
    *   Add customer
    * */
    @PostMapping
    public CustomerSchema add(@Valid @RequestBody Customer request){

        return customerService.add(request);
    }

    /**
     * Get By id
     * */
    @GetMapping(path = "/{id}")
    public CustomerSchema byId(@PathVariable("id") String custId) {

       return customerService.getById(custId);
    }


    /**
     * Place order for a customer Id
     * */
    @PostMapping(path = "/{id}/order")
    public OrderSchema add(@PathVariable("id") String customerId,
                           @RequestBody Order request){

        return orderService.add(customerId, request);

    }

    /**
     * Get By id
     * */
    @GetMapping
    public Customer byCustId(@RequestParam("id") String custId) {

        return !custId.equals("0") ? Customer.builder()
                .firstName("Test")
                .lastName("Test")
                .email("Test")
                .phoneNumber("987654321")
                .build() : Customer.builder().build();
    }



}
