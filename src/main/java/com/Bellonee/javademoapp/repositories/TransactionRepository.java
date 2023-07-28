package com.Bellonee.javademoapp.repositories;

import com.Bellonee.javademoapp.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
