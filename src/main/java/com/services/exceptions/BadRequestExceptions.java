package com.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestExceptions extends RuntimeException {

    public BadRequestExceptions() {
    }

    public BadRequestExceptions(String message) {
        super(message);
    }

    public BadRequestExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestExceptions(Throwable cause) {
        super(cause);
    }
}
