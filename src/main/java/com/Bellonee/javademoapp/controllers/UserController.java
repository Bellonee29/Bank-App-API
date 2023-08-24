package com.Bellonee.javademoapp.controllers;

import com.Bellonee.javademoapp.dto.*;
import com.Bellonee.javademoapp.entities.Transaction;
import com.Bellonee.javademoapp.services.Implementation.TransactionHistoryServiceImpl;
import com.Bellonee.javademoapp.services.UserService;
import com.itextpdf.text.DocumentException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Account APIs")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private  final TransactionHistoryServiceImpl transactionHistoryServiceImpl;


    @Operation(
            summary = "Create New User Account",
            description = "Creating a new user end assigning an account ID"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )

    @PostMapping
    public BankResponse createAccount(@RequestBody UserRequest userRequest){
        return userService.createAccount(userRequest);
    }

    @Operation(
            summary = "Balance Enquiry",
            description = "Given an account number, check how much the user has in his/her account"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )

    @GetMapping("balanceEnquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquireRequest request){
        return userService.balanceEnquiry(request);
    }

    @Operation(
            summary = "Name Enquiry",
            description = "Given an account number, check the name on the account"
    )
    @ApiResponse(
            responseCode = "202",
            description = "Http Status 202 Success"
    )

    @GetMapping("nameEnquiry")
    public String nameEnquiry(@RequestBody EnquireRequest request){
        return userService.nameEnquiry(request);
    }

    @Operation(
            summary = "Credit Account",
            description = "Given an account number and amount, check if the account number valid then credit the account"
    )
    @ApiResponse(
            responseCode = "203",
            description = "Http Status 203 Success"
    )
    @PostMapping("credit")
    public BankResponse creditAccount(@RequestBody CreditDebitRequest request){
        return userService.creditToAccount(request);
    }

    @Operation(
            summary = "Debit Account",
            description = "Given an account number and amount, check if the account number valid then debit the account"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Http Status 204 Success"
    )

    @PostMapping("debit")
    public BankResponse debitAccount(@RequestBody CreditDebitRequest request){
        return userService.DebitAccount(request);
    }


    @Operation(
            summary = "Transfer Money",
            description = "Given an account number and amount, check if the both account number are valid then debit the source account and credit the destination account"
    )
    @ApiResponse(
            responseCode = "205",
            description = "Http Status 205 Success"
    )
    @PostMapping("transfer")
    public BankResponse transfer(@RequestBody TransferRequest request){

        return userService.transfer(request);
    }

    @GetMapping("/generateHistory")
    public List<Transaction> generateTransactionHistory(@RequestParam String accountNumber,
                                                        @RequestParam String startDate,
                                                        @RequestParam String endDate) throws DocumentException, FileNotFoundException {
        return transactionHistoryServiceImpl.generateTransactionHistory(accountNumber, startDate,endDate);
    }
}
