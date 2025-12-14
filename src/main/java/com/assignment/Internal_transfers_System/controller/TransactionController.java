package com.assignment.Internal_transfers_System.controller;

import com.assignment.Internal_transfers_System.dto.TransactionRequest;
import com.assignment.Internal_transfers_System.dto.TransferResponse;
import com.assignment.Internal_transfers_System.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransferService transferService;

    public TransactionController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<TransferResponse> create(@Valid @RequestBody TransactionRequest request) {
        Long transferId = transferService.moveMoney(
                request.getSource_account_id(),
                request.getDestination_account_id(),
                request.getAmount());
        return ResponseEntity.status(HttpStatus.CREATED).body(new TransferResponse(transferId));
    }
}

