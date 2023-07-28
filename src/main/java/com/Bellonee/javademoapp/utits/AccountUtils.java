package com.Bellonee.javademoapp.utits;


import java.time.Year;

public class AccountUtils {
    public static final String ACCOUNT_EXISTS_CODE = "001";
    public static final String ACCOUNT_EXISTS_MESSAGE ="This user already has an account created!";
    public  static final String ACCOUNT_CREATION_SUCCESS = "02";
    public static final String ACCOUNT_CREATION_MESSAGE = "Account has been successfully created! ";
    public static final String ACCOUNT_NOT_EXIST_CODE = "003";
    public static final String ACCOUNT_NOT_EXIST_MESSAGE = "User with the provided Account Number does not Exist";
    public static final String ACCOUNT_FOUND_CODE = "004";
    public static final String ACCOUNT_FOUND_MESSAGE = "User Account Found";
    public static  final String ACCOUNT_CREDITED_CODE= "05";
    public static final String ACCOUNT_CREDITED_SUCCESS_MESSAGE = "User Account was credited successfully";
    public static  final  String INSUFFICIENT_BALANCE_CODE = "006";
    public static final String INSUFFICIENT_BALANCE_MESSAGE = "Insufficient Found";
    public static final String ACCOUNT_DEBITED_CODE = "07";
    public static final String ACCOUNT_DEBITED_MESSAGE = "Account as been successfully debited";

    public static  final String TRANSFER_SUCCESSFUL_CODE = "08";
    public static final String TRANSFER_SUCCESSFUL_MESSAGE = "Transfer was successful";

    public  static String generateAccountNumber(){
        Year currentYear = Year.now();
        int min = 100000;
        int max = 999999;
        int randNumber = (int) Math.floor(Math.random() * (max - min) + min);
        String year = String.valueOf(currentYear);
        String randomNumber = String.valueOf(randNumber);
        StringBuilder accountNumber = new StringBuilder();
        return accountNumber.append(year).append(randomNumber).toString();
    }
}
