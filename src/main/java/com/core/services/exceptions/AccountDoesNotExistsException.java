package com.core.services.exceptions;


public class AccountDoesNotExistsException extends RuntimeException {

    public AccountDoesNotExistsException(Throwable cause) {
        super(cause);
    }

    public AccountDoesNotExistsException(String message) {
        super(message);
    }

    public AccountDoesNotExistsException() {

    }

    public AccountDoesNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountDoesNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
