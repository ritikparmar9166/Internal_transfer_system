package com.assignment.Internal_transfers_System.dto;

import com.assignment.Internal_transfers_System.entity.Account;

public class AccountResponse {
    private final Long account_id;
    private final String balance;

    public AccountResponse(Long accountId, String balance) {
        this.account_id = accountId;
        this.balance = balance;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public String getBalance() {
        return balance;
    }

    public static AccountResponse from(Account account) {
        return new AccountResponse(account.getAccountId(), account.getBalance().toPlainString());
    }
}

