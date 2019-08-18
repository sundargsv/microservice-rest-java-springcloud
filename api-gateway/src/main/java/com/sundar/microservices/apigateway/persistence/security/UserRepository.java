package com.sundar.microservices.apigateway.persistence.security;

import com.sundar.microservices.apigateway.persistence.security.schema.UserSchema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserSchema, String> {

    // allows insert, get by id etc.
    UserSchema findByUserName(String userName);
}
