package com.assignment.Internal_transfers_System.entity;

import com.assignment.Internal_transfers_System.exception.InsufficientFundsException;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", unique = true, nullable = false, updatable = false)
    private Long accountId;

    @Column(nullable = false, precision = 19, scale = 5)
    private BigDecimal balance;

    @Version
    private Long version;

    public Account() {
    }

    public Account(Long accountId, BigDecimal initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void credit(BigDecimal amt) {
        this.balance = balance.add(amt);
    }

    public void debit(BigDecimal amt) {
        if (balance.compareTo(amt) < 0) {
            throw new InsufficientFundsException(accountId, amt);
        }
        this.balance = balance.subtract(amt);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                '}';
    }
}