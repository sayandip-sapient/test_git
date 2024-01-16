package com.bank_customer.bank_customer.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank_customer.bank_customer.DTO.Deposit;
import com.bank_customer.bank_customer.DTO.Withdraw;
import com.bank_customer.bank_customer.Model.Customer;
import com.bank_customer.bank_customer.Model.Transaction;
import com.bank_customer.bank_customer.Service.DAO.Customer_interface;
import com.bank_customer.bank_customer.Service.Repo.CustomerRepo;

import java.util.List;

import javax.management.loading.ClassLoaderRepository;

// import com.vaishali.CustomerService.Model.Transaction;
// import com.vaishali.CustomerService.Dto.DepositRequest;
// import com.vaishali.CustomerService.Dto.WithdrawRequest;
// import com.vaishali.CustomerService.Model.Customer;
// import com.vaishali.CustomerService.Repository.CustomerRepository;
// import com.vaishali.CustomerService.Service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/banking-api/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
    @Autowired
	CustomerRepo customerRepository;

	@Autowired
	Customer_interface customerService;

	@GetMapping("getCustomerByAadhar/{aadharNo}")
	public ResponseEntity<Customer> getCustomerByAadhar(@PathVariable ("aadharNo") String aadharNo) {
		return customerService.getCustomerByAadhar(aadharNo);
	} 

	@PostMapping("/signUp")
	public ResponseEntity<Customer> createNewUser(@Valid @RequestBody Customer newCustomer) {
		return customerService.createNewUser(newCustomer);
	}
	
	@PostMapping("/deposit/{aadharNo}")
	public ResponseEntity<Customer> depositMoney(@PathVariable ("aadharNo") String aadharNo, @RequestBody Deposit depositAmount) {
		return customerService.depositMoney(aadharNo, depositAmount);
	}

	@PostMapping("/withdraw/{aadharNo}")
	public ResponseEntity<Customer> withdrawMoney(@PathVariable ("aadharNo") String aadharNo, @RequestBody Withdraw withdrawRequest) {
		return customerService.withdrawMoney(aadharNo, withdrawRequest);
	}

	// @GetMapping("/{aadharNo}/transactions")
	// public ResponseEntity<List<Transaction>> getCustomerTransactions(@PathVariable String aadharNo) {
	// 	return customerService.getCustomerTransactions(aadharNo);
	// }
    
}
