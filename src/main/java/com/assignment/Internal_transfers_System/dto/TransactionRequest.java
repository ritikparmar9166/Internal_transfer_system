package com.assignment.Internal_transfers_System.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class TransactionRequest {
    @NotNull
    private Long source_account_id;

    @NotNull
    private Long destination_account_id;

    @NotNull
    @Positive
    @Digits(integer = 14, fraction = 5)
    private BigDecimal amount;

    public Long getSource_account_id() {
        return source_account_id;
    }

    public Long getDestination_account_id() {
        return destination_account_id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setSource_account_id(Long source_account_id) {
        this.source_account_id = source_account_id;
    }

    public void setDestination_account_id(Long destination_account_id) {
        this.destination_account_id = destination_account_id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

