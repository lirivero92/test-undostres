package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class EtAuthExcepcion extends RuntimeException {

    public EtAuthExcepcion(String message) {
        super(message);
    }
}
