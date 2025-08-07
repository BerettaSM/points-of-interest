package com.xyz.gps.poi.services.exceptions;

import org.springframework.http.HttpStatus;

import com.xyz.gps.poi.exceptions.ApplicationException;

public class ResourceNotFoundException extends ApplicationException {

    public ResourceNotFoundException() {
        this("Resource not found.");
    }

    public ResourceNotFoundException(String message) {
        this(message, HttpStatus.NOT_FOUND);
    }

    public ResourceNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }

}
