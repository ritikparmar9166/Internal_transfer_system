package com.assignment.Internal_transfers_System.repository;

import com.assignment.Internal_transfers_System.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}

