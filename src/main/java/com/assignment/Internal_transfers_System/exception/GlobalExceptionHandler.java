package com.assignment.Internal_transfers_System.exception;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiError> handleApi(ApiException ex, ServletWebRequest request) {
        ApiError body = new ApiError(ex.getCode(), ex.getMessage(), request.getRequest().getRequestURI());
        return ResponseEntity.status(ex.getStatus()).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException e, ServletWebRequest req) {
        String msg = "Invalid request";
        if (e.getBindingResult().hasFieldErrors()) {
            var fieldError = e.getBindingResult().getFieldErrors().get(0);
            msg = fieldError.getField() + ": " + fieldError.getDefaultMessage();
        }
        log.debug("Validation failed: {}", msg);

        ApiError error = new ApiError("VALIDATION_ERROR", msg, req.getRequest().getRequestURI());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleUnexpected(Exception ex, ServletWebRequest request) {
        ApiError body = new ApiError("INTERNAL_ERROR", "Unexpected error", request.getRequest().getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}

