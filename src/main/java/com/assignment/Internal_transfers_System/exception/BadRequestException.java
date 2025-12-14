package com.assignment.Internal_transfers_System.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException {
    public BadRequestException(String msg) {
        super("BAD_REQUEST", msg, HttpStatus.BAD_REQUEST);
    }
}

