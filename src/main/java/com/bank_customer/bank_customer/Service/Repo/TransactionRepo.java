package com.bank_customer.bank_customer.Service.Repo;

import org.springframework.stereotype.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.bank_customer.bank_customer.Model.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Integer>{
    Transaction findByTransactionId(Integer transactionId);

    @Query(value = "SELECT * FROM transaction WHERE aadhar_no=?1", nativeQuery = true)
    List <Transaction> findByAadharNo(String aadharNo);
}
