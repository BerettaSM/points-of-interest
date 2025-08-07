package com.xyz.gps.poi.exceptions;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
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
        path = getCurrentPath();
    }

    private String getCurrentPath() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .map(req -> ((ServletRequestAttributes) req))
                .map(ServletRequestAttributes::getRequest)
                .map(HttpServletRequest::getRequestURI)
                .orElse("Unknown path");
    }

}
