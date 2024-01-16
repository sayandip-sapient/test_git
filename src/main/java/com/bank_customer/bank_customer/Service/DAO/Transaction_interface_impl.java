package com.bank_customer.bank_customer.Service.DAO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bank_customer.bank_customer.DTO.TransactionRequest;
import com.bank_customer.bank_customer.Model.Customer;
import com.bank_customer.bank_customer.Model.Transaction;
import com.bank_customer.bank_customer.Service.Repo.CustomerRepo;
import com.bank_customer.bank_customer.Service.Repo.TransactionRepo;
import com.bank_customer.bank_customer.security.user.UserRepository;
import com.bank_customer.bank_customer.security.user.User;



@Service
public class Transaction_interface_impl implements Transaction_interface{

    @Autowired
    TransactionRepo transactionRepo;
      @Autowired
    CustomerRepo customer_repo;
    @Autowired
    UserRepository user_repo;


    // Transaction ts;


    // @Autowired
    // CustomerClient customerClient;

    @Override
    public void createNewTransaction(TransactionRequest createTransaction) {
        System.out.println("Inside Create New Transaction");
        Customer customer = customer_repo.findByAadharNo(createTransaction.getAadharNo());
        // Customer customer = response.getBody();
        System.out.println(customer.getName());
       
        Transaction transaction = new Transaction( createTransaction.getTransactionType(),createTransaction.getTransactionAmount(),customer);

        try {
           Transaction ts= transactionRepo.save(transaction);
           System.out.println(ts);
          
        }
        catch (Exception e) {
            // Log the exception or perform any necessary error handling
            System.out.println("An error occurred: " + e.getMessage());
     
        }
    }

    @Override
    public ResponseEntity<List<Transaction>> showAllTransactionsbyAadharNo(String aadharNo) {
        try {
            System.out.println("Inside showAllTransactions.");
            ResponseEntity<Customer> response = new ResponseEntity<>( customer_repo.findByAadharNo(aadharNo), HttpStatus.OK);
            System.out.println(response);
            Customer customer = response.getBody();
            System.out.println(customer.getName());
        //      Optional<User> user=user_repo.findByEmail(customer.getEmail());
        // System.out.println("Inside Create New Transaction");
        // System.out.println(user);
            List<Transaction> transactions = transactionRepo.findByAadharNo(aadharNo);
            return new ResponseEntity<>(transactions,HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Transaction> getTransactionById(Integer transactionId) {
        try {
            Transaction transaction = transactionRepo.findByTransactionId(transactionId);
            return new ResponseEntity<>(transaction,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @Override
    // public ResponseEntity<Transaction> createNewTransaction(TransactionRequest createTransaction) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'createNewTransaction'");
    // }
    
}
