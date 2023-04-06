package com.bruno.api.exceptions;

public class NotFoundResponseException extends RuntimeException {
    public NotFoundResponseException(String message) {
        super(message);
    }
}
