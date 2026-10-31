package com.accenture.productinfo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * The GlobalExceptionHandler class is a Spring Boot component that provides centralized exception handling
 * across the entire application. It is annotated with @RestControllerAdvice, which makes it a specialized
 * version of @ControllerAdvice that is specifically designed to handle exceptions in RESTful web services.
 *
 * Within this class, there is a method handleBadRequestException that is annotated with
 * @ExceptionHandler(BadRequestException.class). This annotation indicates that this method will handle
 * exceptions of type BadRequestException.
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiErrorResponse> handleBadRequestException(
            Exception exception
    ) {
        BadRequestException raisedException = (BadRequestException) exception;
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                raisedException.getErrorId(),
                raisedException.getErrorMessage(),
                raisedException.getErrorDetails()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
