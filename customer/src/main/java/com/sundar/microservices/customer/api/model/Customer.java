package com.sundar.microservices.customer.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
