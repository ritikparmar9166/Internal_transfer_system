package com.assignment.Internal_transfers_System.controller;

import com.assignment.Internal_transfers_System.dto.AccountResponse;
import com.assignment.Internal_transfers_System.dto.CreateAccountRequest;
import com.assignment.Internal_transfers_System.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreateAccountRequest request) {
        accountService.openAccount(request.getAccount_id(), request.getInitial_balance());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{accountId}")
    public AccountResponse getAccountBalance(@PathVariable Long accountId) {
        return AccountResponse.from(accountService.getAccount(accountId));
    }
}

