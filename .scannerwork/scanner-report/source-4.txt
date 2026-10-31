package com.accenture.productinfo.exception;

import java.util.Map;

/**
 * The BadRequestException class is a custom exception in Java that extends the RuntimeException class.
 * It is used to represent a specific type of error in a Spring Boot application.
 */

public class BadRequestException extends RuntimeException {

    private final int errorId;

    private final String errorMessage;

    private final Map<String, Object> errorDetails;

    public BadRequestException(
            int errorId,
            String errorMessage,
            Map<String, Object> errorDetails
    ) {
        this.errorId = errorId;
        this.errorMessage = errorMessage;
        this.errorDetails = errorDetails;
    }

    public int getErrorId() {
        return errorId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Map<String, Object> getErrorDetails() {
        return errorDetails;
    }
}
