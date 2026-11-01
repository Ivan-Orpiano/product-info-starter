package com.accenture.productinfo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.UncheckedIOException;
import java.util.Collections;

/**
 * Central exception handler.
 * Ensures that API errors have consistent JSON responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles invalid product codes as HTTP 400.
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiErrorResponse> handleBadRequestException(
            BadRequestException exception) {

        ApiErrorResponse response = new ApiErrorResponse(
                exception.getErrorId(),
                exception.getErrorMessage(),
                exception.getErrorDetails()
        );

        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }

    // Handles unreadable or malformed JSON as HTTP 500.
    @ExceptionHandler(UncheckedIOException.class)
    public ResponseEntity<ApiErrorResponse> handleFileReadException(
            UncheckedIOException exception) {

        ApiErrorResponse response = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Unable to read JSON file.",
                Collections.emptyMap()
        );
        return new ResponseEntity<>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
