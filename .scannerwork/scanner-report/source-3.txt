package com.accenture.productinfo.exception;

import java.util.Map;

/**
 *The ApiErrorResponse class is a Java class designed to encapsulate error information in a structured format.
 * It is used to represent error responses in a Spring Boot application.
 */
public class ApiErrorResponse {

    private int errorCode;

    private String errorMessage;

    private Map<String, Object> errorDetails;

    public ApiErrorResponse() {
    }

    public ApiErrorResponse(
            int errorCode,
            String errorMessage,
            Map<String, Object> errorDetails
    ) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDetails = errorDetails;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Map<String, Object> getErrorDetails() {
        return errorDetails;
    }
}
