package com.assignment.Internal_transfers_System.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class CreateAccountRequest {

    @NotNull
    private Long account_id;

    @NotNull
    @Positive
    @Digits(integer =14, fraction = 5)
    private BigDecimal initial_balance;

    public Long getAccount_id() {
        return account_id;
    }


    public BigDecimal getInitial_balance() {
        return initial_balance;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public void setInitial_balance(BigDecimal initial_balance) {
        this.initial_balance = initial_balance;
    }
}

