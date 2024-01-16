package com.bank_customer.bank_customer.Service.DAO;




import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.bank_customer.bank_customer.DTO.Deposit;
import com.bank_customer.bank_customer.DTO.TransactionRequest;
import com.bank_customer.bank_customer.DTO.Withdraw;
import com.bank_customer.bank_customer.Model.Customer;
import com.bank_customer.bank_customer.Model.Transaction;

import com.bank_customer.bank_customer.Service.Repo.CustomerRepo;
import com.bank_customer.bank_customer.Service.Repo.TransactionRepo;
import com.bank_customer.bank_customer.security.user.User;
import com.bank_customer.bank_customer.security.user.UserRepository;



@Component
public class Customer_interface_impl implements Customer_interface{
    @Autowired
    CustomerRepo customerRepository;
    @Autowired
    TransactionRepo transactionrepo;
    @Autowired
    Transaction_interface_impl trans_interface;
  @Autowired
    UserRepository user_repo;

    @Override
    public ResponseEntity<Customer> createNewUser(Customer newCustomer) {
        try {
             Optional<User> user=user_repo.findByEmail(newCustomer.getEmail());
        System.out.println("Inside Create New Transaction");
        System.out.println(user);
        if (user.isPresent()) {
  newCustomer.setUniq_val(user.get().getId());
        }
      
			customerRepository.save(newCustomer);
		    return new ResponseEntity<>(newCustomer,HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @Override
    public ResponseEntity<Customer> depositMoney(String aadharNo, Deposit depositAmount) {
        try {
                TransactionRequest transaction_req = new TransactionRequest(aadharNo, depositAmount.getDepositAmount(), "Deposit");
      this.trans_interface.createNewTransaction(transaction_req);

			System.out.println(aadharNo);
			System.out.println("---------- X ----------- X ---------- X ---------");
			System.out.println("Deposit Amount: "+ depositAmount.getDepositAmount());
			Customer cus = customerRepository.findByAadharNo(aadharNo);
			float balance = cus.getBalance();
			balance += depositAmount.getDepositAmount();
			cus.setBalance(balance);
			customerRepository.save(cus);
			return new ResponseEntity<>(cus,HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @Override
    public ResponseEntity<Customer> withdrawMoney(String aadharNo, Withdraw withdrawRequest) {
        try {
            TransactionRequest transaction_req = new TransactionRequest(aadharNo, withdrawRequest.getWithdrawAmount(), "Withdraw");
           trans_interface.createNewTransaction(transaction_req);
			System.out.println(aadharNo);
			System.out.println("---------- X ----------- X ---------- X ---------");
			System.out.println("Withdraw Amount: "+ withdrawRequest.getWithdrawAmount());
			Customer cus = customerRepository.findByAadharNo(aadharNo);
			float balance = cus.getBalance();
			balance -= withdrawRequest.getWithdrawAmount();
			cus.setBalance(balance);
			customerRepository.save(cus);
			return new ResponseEntity<>(cus,HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	@Override
	public ResponseEntity<Customer> getCustomerByAadhar(String aadharNo) {
		Customer customer = customerRepository.findById(aadharNo).get();
		// for (Transaction t: customer.getTransactions()) {
		// 	System.out.println(t);
		// }
		try{
			return new ResponseEntity<>(customer,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @Override
    public ResponseEntity<List<Transaction>> getCustomerTransactions(String aadharNo) {
        return trans_interface.showAllTransactionsbyAadharNo(aadharNo);
    }

   
}
