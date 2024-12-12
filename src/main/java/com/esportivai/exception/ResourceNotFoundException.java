package com.esportivai.exception;


import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    // Constructor to pass a custom message
    public ResourceNotFoundException(String message) {
        super(message);
    }

    // Constructor to pass a custom message with a resource ID (optional)
    public ResourceNotFoundException(String resource, String id) {
        super(String.format("%s with id %s not found", resource, id));
    }

    public ResourceNotFoundException() {

    }
}
