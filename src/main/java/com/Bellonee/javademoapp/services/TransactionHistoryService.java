package com.Bellonee.javademoapp.services;

import com.Bellonee.javademoapp.dto.TransactionDto;
import com.Bellonee.javademoapp.entities.Transaction;
import com.itextpdf.text.DocumentException;
import org.springframework.data.domain.Page;

import java.io.FileNotFoundException;
import java.util.List;

public interface TransactionHistoryService {
    List<Transaction> generateTransactionHistory(String accountNumber, String startDate, String endDate) throws FileNotFoundException, DocumentException;
}
