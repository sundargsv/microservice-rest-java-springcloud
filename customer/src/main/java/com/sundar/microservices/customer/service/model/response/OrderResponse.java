package com.sundar.microservices.customer.service.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private String id;

    // the below is the customerId
    private String correlationId;

    //details of the product like id, name, count, price, discount,
    // but only storing the name as of now
    private List<String> product;

    private Double price;

    private Boolean isDiscounted;

    private Double discountedPrice;

    private Integer version;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;
}
