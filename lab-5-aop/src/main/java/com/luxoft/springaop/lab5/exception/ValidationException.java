package com.luxoft.springaop.lab5.exception;

public class ValidationException  extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
