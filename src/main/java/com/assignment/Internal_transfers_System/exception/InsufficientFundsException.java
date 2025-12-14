package com.assignment.Internal_transfers_System.exception;

import org.springframework.http.HttpStatus;
import java.math.BigDecimal;

public class InsufficientFundsException extends ApiException {
    public InsufficientFundsException(Long accountId, BigDecimal attempted) {
        super("INSUFFICIENT_FUNDS",
                "Can not do transction form "+ accountId,
                HttpStatus.BAD_REQUEST);
    }
}

