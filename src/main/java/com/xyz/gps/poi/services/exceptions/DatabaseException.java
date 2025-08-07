package com.xyz.gps.poi.services.exceptions;

import org.springframework.http.HttpStatus;

import com.xyz.gps.poi.exceptions.ApplicationException;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class DatabaseException extends ApplicationException {

    public DatabaseException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }
    
    public DatabaseException(String message, HttpStatus status) {
        super(message, status);
    }

}
