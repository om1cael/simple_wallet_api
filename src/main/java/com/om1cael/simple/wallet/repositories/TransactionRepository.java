package com.om1cael.simple.wallet.repositories;

import com.om1cael.simple.wallet.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
