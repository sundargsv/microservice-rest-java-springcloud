package com.sundar.microservices.auth.api;

import com.sundar.microservices.auth.api.model.AuthRequest;
import com.sundar.microservices.auth.api.model.AuthResponse;
import com.sundar.microservices.auth.api.model.UserRequest;
import com.sundar.microservices.auth.common.Constants;
import com.sundar.microservices.auth.persistence.Schema.UserSchema;
import com.sundar.microservices.auth.service.UserDetailsServiceImpl;
import com.sundar.microservices.auth.service.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {Constants.API_AUTH_PATH}, produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags="Authentication", description="Operations pertaining to auth")
@Slf4j
public class AuthenticationApi {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping(value = "/authenticate")
    public AuthResponse createAuthenticationToken(@RequestBody AuthRequest request) throws Exception{

        log.info("Creating auth token for a given user name {}", request.getUserName());
        authenticate(request.getUserName(), request.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new AuthResponse(token);
    }

    @PostMapping(value = "/register")
    public UserSchema saveUser(@RequestBody UserRequest request) throws Exception {

        log.info("Registering the user {}", request);
        return userDetailsService.save(request);
    }

    /**
     * Helper functions here...
     * */
    private void authenticate(String username, String password) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

