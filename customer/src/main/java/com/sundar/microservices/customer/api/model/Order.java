package com.sundar.microservices.customer.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sundar.microservices.customer.api.annotations.VerifyDiscountedPrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

    //details of the product like id, name, count, price, discount,
    // but only storing the name as of now
    private List<String> product;

    private Double price;

    private Boolean isDiscounted;

    @VerifyDiscountedPrice
    private Double discountedPrice;
}
