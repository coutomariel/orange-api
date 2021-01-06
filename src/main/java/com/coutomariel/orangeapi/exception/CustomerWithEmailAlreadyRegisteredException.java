package com.coutomariel.orangeapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerWithEmailAlreadyRegisteredException extends RuntimeException {
    public CustomerWithEmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
