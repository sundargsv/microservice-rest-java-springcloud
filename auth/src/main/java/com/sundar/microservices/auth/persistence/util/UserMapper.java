package com.sundar.microservices.auth.persistence.util;

import com.sundar.microservices.auth.api.model.UserRequest;
import com.sundar.microservices.auth.persistence.Schema.UserSchema;

public class UserMapper {

    public static UserSchema toUserSchema(UserRequest request){
        return UserSchema.builder()
                .firstName(request.getUserName())
                .userName(request.getUserName())
                .build();
    }

}
