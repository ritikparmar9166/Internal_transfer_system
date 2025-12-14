package com.assignment.Internal_transfers_System.service;

import com.assignment.Internal_transfers_System.entity.Account;
import com.assignment.Internal_transfers_System.exception.ConflictException;
import com.assignment.Internal_transfers_System.exception.NotFoundException;
import com.assignment.Internal_transfers_System.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void openAccount(Long accountId, BigDecimal initialBalance) {
        if (accountRepository.findByAccountId(accountId).isPresent()) {
            throw new ConflictException("Account " + accountId + " already exists");
        }

        log.info("opening account with id {} and balance {} ", accountId, initialBalance);
        accountRepository.save(new Account(accountId, initialBalance));
    }

    @Transactional(readOnly = true)
    public Account getAccount(Long accountId) {
        return accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> new NotFoundException("Account" + accountId + " not found"));
    }
}

