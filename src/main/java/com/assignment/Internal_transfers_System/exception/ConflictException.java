package com.assignment.Internal_transfers_System.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends ApiException {
    public ConflictException(String msg) {
        super("CONFLICT", msg, HttpStatus.CONFLICT);
    }
}

