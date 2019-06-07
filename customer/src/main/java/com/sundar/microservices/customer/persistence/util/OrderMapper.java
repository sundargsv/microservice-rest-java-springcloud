package com.sundar.microservices.customer.persistence.util;

import com.sundar.microservices.customer.api.model.Order;
import com.sundar.microservices.customer.common.Helper;
import com.sundar.microservices.customer.persistence.Schema.OrderSchema;

public class OrderMapper {

    public static OrderSchema toOrderSchema(String customerId, Order order){
        return OrderSchema.builder()
                .customerId(customerId)
                .product(order.getProduct())
                .price(order.getPrice())
                .isDiscounted(order.getIsDiscounted())
                .discountedPrice(order.getDiscountedPrice())
                .createdDate(Helper.getLocalUtcDateTime())
                .modifiedDate(Helper.getLocalUtcDateTime())
                .build();
    }
}
