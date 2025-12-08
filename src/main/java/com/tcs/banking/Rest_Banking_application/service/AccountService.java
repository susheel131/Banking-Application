package com.tcs.banking.Rest_Banking_application.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.banking.Rest_Banking_application.entity.Account;
import com.tcs.banking.Rest_Banking_application.entity.User;
import com.tcs.banking.Rest_Banking_application.repository.AccountRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService {
	
    
    @Autowired
    private AccountRepository accountRepository;
    
    
    @Transactional
    public Account createAccount(User user) {
    	Account account=new Account();
        // Generate unique account number (e.g., ACC + timestamp + random 4-digit number)
        String accountNumber = "ACC" + System.currentTimeMillis() + new Random().nextInt(9000) + 1000;  // Ensures uniqueness
        account.setAccountNumber(accountNumber);
        account.setBalance(0);
        account.setUser(user);
        
        // Save and return
        return accountRepository.save(account);
    }
}