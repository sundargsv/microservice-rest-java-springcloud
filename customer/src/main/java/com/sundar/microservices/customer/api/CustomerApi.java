package com.sundar.microservices.customer.api;

import com.sundar.microservices.customer.api.model.Customer;
import com.sundar.microservices.customer.common.Constants;
import com.sundar.microservices.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = {Constants.API_CUSTOMER_PATH}, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class CustomerApi {

    @Autowired
    @Qualifier("customer-service")
    private CustomerService customerService;

    /*
    *   Add customer
    * */
    @PostMapping
    public Customer add(@RequestBody Customer request){

        return customerService.add(request);
    }

    /**
     * Get By id
     * */
    @GetMapping(path = "/{id}")
    public Customer byId(@PathVariable("id") String custId) {

       return customerService.getById(custId);
    }

    /**
     * Get By id
     * */
    @GetMapping
    public Customer byCustId(@RequestParam("id") String custId) {

        return !custId.equals("0") ? Customer.builder()
                .id(custId)
                .firstName("Test")
                .lastName("Test")
                .email("Test")
                .phoneNumber("987654321")
                .build() : Customer.builder().build();
    }



}
