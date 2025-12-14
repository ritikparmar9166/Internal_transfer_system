package com.assignment.Internal_transfers_System.dto;

public class TransferResponse {
    private final Long transaction_id;

    public TransferResponse(Long transactionId) {
        this.transaction_id = transactionId;
    }

    public Long getTransaction_id() {
        return transaction_id;
    }
}

