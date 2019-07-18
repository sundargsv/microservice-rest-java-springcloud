package com.sundar.microservices.customer.api;

import com.sundar.microservices.customer.api.model.Customer;
import com.sundar.microservices.customer.api.model.ErrorResponse;
import com.sundar.microservices.customer.api.model.Order;
import com.sundar.microservices.customer.common.Constants;
import com.sundar.microservices.customer.persistence.Schema.CustomerSchema;
import com.sundar.microservices.customer.persistence.Schema.OrderSchema;
import com.sundar.microservices.customer.service.CustomerService;
import com.sundar.microservices.customer.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = {Constants.API_CUSTOMER_PATH}, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags="Customer", description="Operations pertaining to customer services")
@Slf4j
public class CustomerApi {

    @Autowired
    @Qualifier("customer-service")
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    /**
     * Add new customer
     * */
    @ApiOperation("Adding new customer")
    @ApiResponses( value = {
            @ApiResponse(code = 200, response = CustomerSchema.class, message = "Customer has been added successfully"),
    }
    )
    @PostMapping
    public CustomerSchema add(@Valid @RequestBody Customer request){

        return customerService.add(request);
    }

    /**
     * Get By id
     * */
    @ApiOperation("Get customer by id")
    @ApiResponses( value = {
            @ApiResponse(code = 200, response = CustomerSchema.class, message = "Fetched customer successfully."),
    }
    )
    @GetMapping(path = "/{id}")
    public CustomerSchema loadCustomer(@PathVariable("id") String customerId) {

       return customerService.getById(customerId);
    }


    /**
     * Integrating all customer-order related api's here
     * */
    @ApiOperation("Adding new order upon a customer id")
    @ApiResponses( value = {
            @ApiResponse(code = 200, response = OrderSchema.class, message = "Order has been added successfully for a customer"),
    }
    )
    @PostMapping(path = "/order/{customerId}")
    public OrderSchema add(@PathVariable("customerId") String id,
                           @RequestBody Order order){
        return orderService.add(id, order);
    }

    @GetMapping(path = "/order/{customerId}")
    public List<OrderSchema> loadOrders(@PathVariable("customerId") String customerId){
        return orderService.load(customerId);
    }

}
