package com.Bellonee.javademoapp.services.Implementation;

import com.Bellonee.javademoapp.dto.EmailDetails;
import com.Bellonee.javademoapp.dto.TransactionDto;
import com.Bellonee.javademoapp.entities.Transaction;
import com.Bellonee.javademoapp.entities.User;
import com.Bellonee.javademoapp.repositories.TransactionHistoryRepository;
import com.Bellonee.javademoapp.repositories.UserRepository;
import com.Bellonee.javademoapp.services.EmailService;
import com.Bellonee.javademoapp.services.TransactionHistoryService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j

public class TransactionHistoryServiceImpl implements TransactionHistoryService {
    private static final String FILE = "/Users/decagon/Desktop/statement/BankStatement.pdf";
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    @Override
    public List<Transaction> generateTransactionHistory(String accountNumber, String startDate, String endDate) throws FileNotFoundException, DocumentException {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
        List<Transaction> transactions = transactionHistoryRepository.findAllByAccountNumberAndCreatedAtBetween(accountNumber,start,end).stream().filter(transaction -> transaction.getAccountNumber().equals(accountNumber))
                .filter(transaction -> transaction.getCreatedAt().isEqual(start))
                .filter(transaction -> transaction.getCreatedAt().isEqual(end))
                .toList();
        User user = userRepository.findByAccountNumber(accountNumber);
        String customerName = user.getFirstName() + " " + user.getLastName()+ " " + user.getOtherName();
        Rectangle statementSize = new Rectangle(PageSize.A4);
        Document document = new Document(statementSize);
        log.info("Setting size of document");
        OutputStream outputStream = new FileOutputStream(FILE);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        PdfPTable bankInfoTable = new PdfPTable(1);
        PdfPCell bankName = new PdfPCell(new Phrase("Bellonee Bank "));
        bankName.setBorder(0);
        bankName.setBackgroundColor(BaseColor.BLUE);
        bankName.setPadding(20f);

        PdfPCell bankAddress = new PdfPCell(new Phrase(" 17, ETP Benin City Nigeria"));
        bankAddress.setBorder(0);
        bankInfoTable.addCell(bankName);
        bankInfoTable.addCell(bankAddress);

        PdfPTable statementInfo = new PdfPTable(2);

        PdfPCell customerInfo = new PdfPCell(new Phrase("Start Date: " + startDate));
        customerInfo.setBorder(0);
        PdfPCell statement = new PdfPCell(new Phrase("STATEMENT OF ACCOUNT"));
        statement.setBorder(0);
        PdfPCell stopDate = new PdfPCell(new Phrase("END DATE: " + endDate));
        stopDate.setBorder(0);
        PdfPCell name = new PdfPCell(new Phrase("CUSTOMER NAME: " + customerName));
        name.setBorder(0);
        PdfPCell space = new PdfPCell();
        space.setBorder(0);

        PdfPCell address = new PdfPCell(new Phrase("Customer Addresss" + user.getAccountNumber()));
        address.setBorder(0);

        // Creating Transaction Table
        PdfPTable transactionTable = new PdfPTable(4);
        PdfPCell date = new PdfPCell(new Phrase("DATE"));
        date.setBackgroundColor(BaseColor.BLUE);
        date.setBorder(0);

        PdfPCell transactionType = new PdfPCell(new Phrase("TRANSACTION TYPE"));
        transactionType.setBackgroundColor(BaseColor.BLUE);
        transactionType.setBorder(0);

        PdfPCell amount = new PdfPCell(new Phrase("TRANSACTION AMOUNT"));
        amount.setBackgroundColor(BaseColor.BLUE);
        amount.setBorder(0);

        PdfPCell status =new PdfPCell(new Phrase("STATUS"));
        status.setBackgroundColor(BaseColor.BLUE);
        status.setBorder(0);

        transactionTable.addCell(date);
        transactionTable.addCell(transactionType);
        transactionTable.addCell(amount);
        transactionTable.addCell(status);


        // To Stream in all fetch transaction in to the table

        transactions.forEach(transaction -> {
            transactionTable.addCell(new Phrase(transaction.getCreatedAt().toString()));
            transactionTable.addCell(new Phrase(transaction.getTransactionType()));
            transactionTable.addCell(new Phrase(transaction.getAmount().toString()));
            transactionTable.addCell(new Phrase(transaction.getStatus()));
        });

        statementInfo.addCell(customerInfo);
        statementInfo.addCell(statement);
        statementInfo.addCell(endDate);
        statementInfo.addCell(name);
        statementInfo.addCell(space);
        statementInfo.addCell(address);

        document.add(bankInfoTable);
        document.add(statementInfo);
        document.add(transactionTable);

        document.close();

        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(user.getEmail())
                .messageBody("STATEMENT OF ACCOUNT")
                .subject("Kindly find your requested statement of account")
                .attachment(FILE)
                .build();
        emailService.sendEmailWithAttachment(emailDetails);

        return (transactions);
    }

}
