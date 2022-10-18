package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EtNOT_FOUNDExepcion extends RuntimeException {

    public EtNOT_FOUNDExepcion(String message) {
        super(message);
    }
}
