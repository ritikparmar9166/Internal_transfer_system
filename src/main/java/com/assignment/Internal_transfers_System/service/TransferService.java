package com.assignment.Internal_transfers_System.service;

import com.assignment.Internal_transfers_System.entity.Account;
import com.assignment.Internal_transfers_System.entity.Transfer;
import com.assignment.Internal_transfers_System.exception.BadRequestException;
import com.assignment.Internal_transfers_System.exception.NotFoundException;
import com.assignment.Internal_transfers_System.repository.AccountRepository;
import com.assignment.Internal_transfers_System.repository.TransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TransferService {

    private static final Logger log = LoggerFactory.getLogger(TransferService.class);
    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;

    public TransferService(AccountRepository accountRepository,
                                 TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    @Transactional
    public Long moveMoney(Long fromAcctId, Long toAcctId, BigDecimal amt) {

        if (fromAcctId.equals(toAcctId)) {
            throw new BadRequestException("Source and destination accounts cannot be the same");
        }

        Account fromAcct = accountRepository.findWithLock(fromAcctId)
                .orElseThrow(() -> new NotFoundException("Source account not found: " + fromAcctId));
        Account toAcct = accountRepository.findWithLock(toAcctId)
                .orElseThrow(() -> new NotFoundException("Destination account not found " + toAcctId));


        // normalizing to 5 decimal places because user input may have more than 5 decimal places
        BigDecimal transferAmt = amt.setScale(5, RoundingMode.HALF_UP);

        fromAcct.debit(transferAmt);
        toAcct.credit(transferAmt);

        accountRepository.save(fromAcct);
        accountRepository.save(toAcct);


        Transfer record = new Transfer(fromAcctId, toAcctId, transferAmt);
        transferRepository.save(record);
        log.info("Transfer from {} to {}", fromAcctId, toAcctId);
        
        return record.getId();
    }
}