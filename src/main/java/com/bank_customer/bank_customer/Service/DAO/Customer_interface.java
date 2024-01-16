package com.bank_customer.bank_customer.Service.DAO;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bank_customer.bank_customer.DTO.Deposit;
import com.bank_customer.bank_customer.DTO.Withdraw;
import com.bank_customer.bank_customer.Model.Customer;
import com.bank_customer.bank_customer.Model.Transaction;



public interface Customer_interface {
    public ResponseEntity<Customer> createNewUser(Customer newCustomer);

    public ResponseEntity<Customer> getCustomerByAadhar(String aadharNo);

    public ResponseEntity<Customer> depositMoney(String aadharNo, Deposit depositAmount);

    public ResponseEntity<Customer> withdrawMoney(String aadharNo, Withdraw withdrawRequest);

    public ResponseEntity<List<Transaction>> getCustomerTransactions(String aadharNo);
}
