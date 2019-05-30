package com.sundar.microservices.customer.persistence.util;

import com.sundar.microservices.customer.api.model.Customer;
import com.sundar.microservices.customer.common.Helper;
import com.sundar.microservices.customer.persistence.Schema.CustomerSchema;

public class CustomerMapper {

    public static CustomerSchema toCustomerSchema(Customer customer){
        return CustomerSchema.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phoneNumber(customer.getPhoneNumber())
                .email(customer.getEmail())
                .createdDate(Helper.getLocalUtcDateTime())
                .modifiedDate(Helper.getLocalUtcDateTime())
                .build();
    }
}
