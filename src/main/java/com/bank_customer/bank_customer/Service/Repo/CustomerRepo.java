package com.bank_customer.bank_customer.Service.Repo;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bank_customer.bank_customer.Model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,String>{
    Customer findByAadharNo(String aadharNo);
    ArrayList<Customer> findByName(String name);
    ArrayList<Customer> findByEmail(String email);
    ArrayList<Customer> findByPhone(String phone);
}
