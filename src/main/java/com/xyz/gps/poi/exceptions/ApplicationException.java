package com.xyz.gps.poi.exceptions;

import org.springframework.http.HttpStatus;

import com.xyz.gps.poi.util.PathUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApplicationException extends RuntimeException {

    protected HttpStatus httpStatus;
    protected String path;

    public ApplicationException() {
        this("Oops! Something went wrong.");
    }

    public ApplicationException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ApplicationException(String message, HttpStatus status) {
        super(message);
        httpStatus = status;
        path = PathUtils.getCurrentPath();
    }

}
