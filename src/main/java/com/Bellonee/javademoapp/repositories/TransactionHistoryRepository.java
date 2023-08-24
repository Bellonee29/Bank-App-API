package com.Bellonee.javademoapp.repositories;

import com.Bellonee.javademoapp.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository <Transaction, String> {
    List<Transaction> findAllByAccountNumberAndCreatedAtBetween(String accountNumber, LocalDate createdAt, LocalDate createdAt2);

   // Arrays findAllByAccountNumberAndCreatedAtBetween();
}
