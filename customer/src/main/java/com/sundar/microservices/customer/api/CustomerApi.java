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
            @ApiResponse(code = 404, response = ErrorResponse.class, message = "Customer not found error."),
            @ApiResponse(code = 400, response = ErrorResponse.class, message = "Bad Request."),
            @ApiResponse(code = 401, response = ErrorResponse.class, message = "Unauthorized."),
            @ApiResponse(code = 403, response = ErrorResponse.class, message = "Forbidden."),
            @ApiResponse(code = 500, response = ErrorResponse.class, message = "System error.")
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
            @ApiResponse(code = 404, response = ErrorResponse.class, message = "Customer not found error."),
            @ApiResponse(code = 400, response = ErrorResponse.class, message = "Bad Request."),
            @ApiResponse(code = 401, response = ErrorResponse.class, message = "Unauthorized."),
            @ApiResponse(code = 403, response = ErrorResponse.class, message = "Forbidden."),
            @ApiResponse(code = 500, response = ErrorResponse.class, message = "System error.")
    }
    )
    @GetMapping(path = "/{id}")
    public CustomerSchema byId(@PathVariable("id") String customerId) {

       return customerService.getById(customerId);
    }

}
