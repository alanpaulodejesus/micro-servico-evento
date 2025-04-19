package com.evento.evento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataEventoInvalidaException extends RuntimeException {
    public DataEventoInvalidaException(String message) {
        super( String.valueOf( message ));
    }
}
