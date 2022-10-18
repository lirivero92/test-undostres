package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED)
public class ConsultExepcion extends Throwable {

    public ConsultExepcion(String message) {
        super(message);
    }
}
