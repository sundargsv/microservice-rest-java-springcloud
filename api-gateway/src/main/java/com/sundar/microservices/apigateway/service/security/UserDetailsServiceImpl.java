package com.sundar.microservices.apigateway.service.security;

import com.sundar.microservices.apigateway.persistence.security.UserRepository;
import com.sundar.microservices.apigateway.persistence.security.schema.UserSchema;
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

}
