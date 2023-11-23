package com.example.springboot23.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private final String id;


    public ResourceNotFoundException(String message, String id) {
        super(message);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
