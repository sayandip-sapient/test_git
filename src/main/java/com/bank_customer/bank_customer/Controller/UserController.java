package com.bank_customer.bank_customer.Controller;
import java.util.List;
import java.util.Optional;

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
import com.bank_customer.bank_customer.security.user.User;
import com.bank_customer.bank_customer.security.user.UserRepository;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController{
    @Autowired
    UserRepository user_repo;
    
    // @PostMapping("/new")
    // public ResponseEntity<Transaction> createNewTransaction(@RequestBody TransactionRequest createTransaction) {
    //     return transactionService.createNewTransaction(createTransaction);
    // }

    @GetMapping("/user/{email}")
    public ResponseEntity<?> showUserwithemail(@PathVariable("email") String email) {
        Optional<User> userOptional = user_repo.findByEmail(email);
    
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // If needed, you can set newCustomer's uniq_val here
            // newCustomer.setUniq_val(user.getId());
    
            // Return the User with HTTP status 200 (OK)
            return ResponseEntity.ok(user);
        } else {
            // If the user is not found, return HTTP status 404 (Not Found)
            return ResponseEntity.notFound().build();
        }
    }
    
//     }
    }
 
