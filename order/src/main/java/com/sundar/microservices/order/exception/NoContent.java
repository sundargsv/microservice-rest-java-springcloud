package com.sundar.microservices.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoContent extends ResponseStatusException {
    public NoContent(String reason) {
        super(HttpStatus.NO_CONTENT, reason);
    }

    public NoContent(String reason, Throwable cause) {
        super(HttpStatus.NO_CONTENT, reason, cause);
    }
}
