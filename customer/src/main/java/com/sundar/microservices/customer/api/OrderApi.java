package com.sundar.microservices.customer.api;

import com.sundar.microservices.customer.common.Constants;
import com.sundar.microservices.customer.exception.BadRequest;
import com.sundar.microservices.customer.exception.InternalServerError;
import com.sundar.microservices.customer.exception.NotFound;
import com.sundar.microservices.customer.persistence.Schema.OrderSchema;
import com.sundar.microservices.customer.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = Constants.API_ORDER_PATH)
@Api(tags="Orders", description="Operations pertaining to order services")
@Slf4j
public class OrderApi {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "Get orders by id", response = OrderSchema.class)
    @ApiResponses( value = {
            @ApiResponse(code = 404, response = NotFound.class, message = "Order not found error."),
            @ApiResponse(code = 400, response = BadRequest.class, message = "Bad Request."),
            @ApiResponse(code = 500, response = InternalServerError.class, message = "System error.")
    }
    )
    @GetMapping(path = "{orderId}")
    public OrderSchema getByOrderId(@PathVariable("orderId") String id){

        return orderService.load(id);
    }
}
