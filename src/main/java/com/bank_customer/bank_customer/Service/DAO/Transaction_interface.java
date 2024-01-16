package com.bank_customer.bank_customer.Service.DAO;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bank_customer.bank_customer.DTO.TransactionRequest;
import com.bank_customer.bank_customer.Model.Transaction;

public interface Transaction_interface {

    public void createNewTransaction(TransactionRequest createTransaction);

      public ResponseEntity<List<Transaction>> showAllTransactionsbyAadharNo(String aadharNo);

      public ResponseEntity<Transaction> getTransactionById(Integer transactionId);
} 
