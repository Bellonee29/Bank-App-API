package com.Bellonee.javademoapp.services;

import com.Bellonee.javademoapp.dto.*;


public interface UserService {
    BankResponse createAccount(UserRequest userRequest);
    BankResponse balanceEnquiry(EnquireRequest request);
    String nameEnquiry(EnquireRequest request);
    BankResponse creditToAccount(CreditDebitRequest request);
    BankResponse DebitAccount (CreditDebitRequest request);
    BankResponse transfer(TransferRequest request);
}
