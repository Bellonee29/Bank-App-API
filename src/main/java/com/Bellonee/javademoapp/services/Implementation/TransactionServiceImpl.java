package com.Bellonee.javademoapp.services.Implementation;

import com.Bellonee.javademoapp.dto.TransactionDto;
import com.Bellonee.javademoapp.entities.Transaction;
import com.Bellonee.javademoapp.repositories.TransactionRepository;
import com.Bellonee.javademoapp.services.TransactionService;
import org.springframework.stereotype.Component;

@Component
public class TransactionServiceImpl implements TransactionService {

    //TransactionService transactionService;
    TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void saveTransaction(TransactionDto transactionDto) {
      Transaction transaction = Transaction.builder()
              .transactionType(transactionDto.getTransactionType())
              .accountNumber(transactionDto.getAccountNumber())
              .amount(transactionDto.getAmount())
              .status("SUCCESS")
              .build();
      transactionRepository.save(transaction);
        System.out.println("Transaction saved successfully");
    }
}
