package com.sundar.microservices.auth.persistence;

import com.sundar.microservices.auth.persistence.Schema.UserSchema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserSchema, String> {

    // allows insert, get by id etc.
    UserSchema findByUserName(String userName);
}
