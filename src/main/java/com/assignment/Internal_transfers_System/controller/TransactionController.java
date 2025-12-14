package com.assignment.Internal_transfers_System.controller;

import com.assignment.Internal_transfers_System.dto.TransactionRequest;
import com.assignment.Internal_transfers_System.dto.TransferResponse;
import com.assignment.Internal_transfers_System.service.TransferService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);
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

        TransferResponse response = new TransferResponse(transferId);
        log.info("transfer info {}", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

