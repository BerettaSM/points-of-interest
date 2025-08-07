package com.xyz.gps.poi.domain.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.xyz.gps.poi.util.PathUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ValidationError extends CustomError {

    private final List<FieldErrorEntry> errors = new ArrayList<>();

    public ValidationError(Integer status, String message, Instant timestamp, String path, List<FieldErrorEntry> errors) {
        super(status, message, timestamp, path);
        this.errors.addAll(errors);
    }

    public static ValidationError from(MethodArgumentNotValidException e) {
        List<FieldErrorEntry> errors = extractErrors(e);
        return new ValidationError(
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            "Validation error(s).",
            Instant.now(),
            PathUtils.getCurrentPath(),
            errors);
    }

    private static List<FieldErrorEntry> extractErrors(MethodArgumentNotValidException e) {
        return e.getFieldErrors()
            .stream()
            .collect(
                Collectors.groupingBy(
                    FieldError::getField,
                    Collectors.mapping(
                        FieldError::getDefaultMessage,
                        Collectors.toList())))
            .entrySet()
            .stream()
            .map(FieldErrorEntry::new)
            .toList();
    }

}
