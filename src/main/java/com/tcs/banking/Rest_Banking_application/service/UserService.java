package com.tcs.banking.Rest_Banking_application.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.banking.Rest_Banking_application.entity.User;
import com.tcs.banking.Rest_Banking_application.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountService accountService;
    
    @Transactional
    public User register(User user) {
        userRepository.save(user);
        accountService.createAccount(user); 

        return user;
    }

	public User findByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}
}
