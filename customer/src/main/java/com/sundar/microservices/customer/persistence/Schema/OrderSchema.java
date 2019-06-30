package com.sundar.microservices.customer.persistence.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("order")
public class OrderSchema {

    @Id
    private String id;

    private String customerId;

    //details of the product like id, name, count, price, discount,
    // but only storing the name as of now
    private List<String> product;

    private Double price;

    private Boolean isDiscounted;

    private Double discountedPrice;

    @Version
    private Integer version;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
