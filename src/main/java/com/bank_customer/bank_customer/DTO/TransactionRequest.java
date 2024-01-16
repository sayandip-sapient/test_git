package com.bank_customer.bank_customer.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    String aadharNo;
    float transactionAmount;
    String transactionType;
}