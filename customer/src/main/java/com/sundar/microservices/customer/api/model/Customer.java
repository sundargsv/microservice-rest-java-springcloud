package com.sundar.microservices.customer.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {

    @NotEmpty(message = "First Name can't be empty. Pl try again.")
    @NotNull(message = "First Name can't be null. Pl try again.")
    private String firstName;

    private String lastName;

    @NotEmpty(message = "PhoneNumber can't be empty. Pl try again.")
    @NotNull(message = "PhoneNumber can't be null. Pl try again.")
    private String phoneNumber;

    @Email(message = "Email format error")
    private String email;
}
