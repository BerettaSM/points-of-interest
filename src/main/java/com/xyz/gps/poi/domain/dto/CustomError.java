package com.xyz.gps.poi.domain.dto;

import java.time.Instant;

import com.xyz.gps.poi.exceptions.ApplicationException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CustomError {

    private Integer status;
    private String message;
    private Instant timestamp;
    private String path;

    public static CustomError from(ApplicationException e) {
        return new CustomError(
            e.getHttpStatus().value(),
            e.getMessage(),
            Instant.now(),
            e.getPath());
    } 

}
