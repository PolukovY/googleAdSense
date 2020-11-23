package com.levik.googleadsense.auth.api.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Identity Not Found")
public class IdentityNotFoundException extends RuntimeException {

    public IdentityNotFoundException(String message) {
        super(message);
    }
}
