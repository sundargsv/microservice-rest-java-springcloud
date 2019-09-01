package com.sundar.microservices.auth.service;

import com.sundar.microservices.auth.api.model.UserRequest;
import com.sundar.microservices.auth.persistence.Schema.UserSchema;
import com.sundar.microservices.auth.persistence.UserRepository;
import com.sundar.microservices.auth.persistence.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        //0. fetch User schema by username
        UserSchema savedSchema = userRepository.findByUserName(userName);

        //1. check if user not found
        if(Objects.isNull(savedSchema))
            throw new UsernameNotFoundException(String.format("User not found for the given username %s", userName));

        //2. if user exists
        return new User(savedSchema.getUserName(), savedSchema.getPassword(), new ArrayList<>());
    }

    public UserSchema save(UserRequest request){

        //0. map DTO to DAO
        UserSchema saveSchema = UserMapper.toUserSchema(request);

        //1. encrypt the password and set to schema
        saveSchema.setPassword(bcryptEncoder.encode(request.getPassword()));

        //2. save the schema
        return userRepository.save(saveSchema);

    }
}
