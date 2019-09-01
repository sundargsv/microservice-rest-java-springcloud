package com.sundar.microservices.auth.api.model;

import lombok.Data;

@Data
public class UserRequest {

    // TODO: 8/18/19 Add validations here
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
}
