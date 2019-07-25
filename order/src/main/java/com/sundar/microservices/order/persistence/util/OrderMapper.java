package com.sundar.microservices.order.persistence.util;

import com.sundar.microservices.order.api.model.Order;
import com.sundar.microservices.order.common.Helper;
import com.sundar.microservices.order.persistence.Schema.OrderSchema;

public class OrderMapper {

    public static OrderSchema toOrderSchema(String correlationId, Order order){
        return OrderSchema.builder()
                .correlationId(correlationId)
                .product(order.getProduct())
                .price(order.getPrice())
                .isDiscounted(order.getIsDiscounted())
                .discountedPrice(order.getDiscountedPrice())
                .createdDate(Helper.getLocalUtcDateTime())
                .modifiedDate(Helper.getLocalUtcDateTime())
                .build();
    }
}
