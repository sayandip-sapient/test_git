package com.bank_customer.bank_customer.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank_customer.bank_customer.Model.Transaction;
import com.bank_customer.bank_customer.Service.DAO.Transaction_interface;

@RestController
@RequestMapping("/api/banking-api/transactions")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {
    @Autowired
    Transaction_interface transaction_interface;
    
    // @PostMapping("/new")
    // public ResponseEntity<Transaction> createNewTransaction(@RequestBody TransactionRequest createTransaction) {
    //     return transactionService.createNewTransaction(createTransaction);
    // }

    @GetMapping("/allTransactions/{aadharNo}")
    public ResponseEntity<List<Transaction>> showAllTransactionsbyAadharNo(@PathVariable ("aadharNo") String aadharNo) {
        return transaction_interface.showAllTransactionsbyAadharNo(aadharNo);
    }

    @GetMapping("/getTransactionById/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable ("transactionId") Integer transactionId) {
        return transaction_interface.getTransactionById(transactionId);
    }
}