package com.tcs.banking.Rest_Banking_application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.banking.Rest_Banking_application.entity.Account;
import com.tcs.banking.Rest_Banking_application.entity.Transaction;
import com.tcs.banking.Rest_Banking_application.repository.AccountRepository;
import com.tcs.banking.Rest_Banking_application.repository.TransactionRepository;


@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private TransactionRepository transactionRepository;

    // ------------------- Deposit -------------------
    @Transactional
    public void deposit(String accountNumber, double amount) {
        validateAmount(amount);
        System.out.println("ddgndfkj+++++++++++++++++++++++");
        Account account = getAccount(accountNumber);

        account.setBalance(account.getBalance()+amount);
        accountRepository.save(account);

        saveTransaction(account, "DEPOSIT", amount);
    }

    // ------------------- Withdraw -------------------
    @Transactional
    public void withdraw( String accountNumber,double amount, String password) {
        validateAmount(amount);
        Account account = getAccount(accountNumber);

        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        account.setBalance(account.getBalance()-amount);
        accountRepository.save(account);

        saveTransaction(account, "WITHDRAWAL", amount);
    }

    // ------------------- Transfer -------------------
    @Transactional
    public void transfer(String fromAcc, String toAcc, double amount, String password) {
        validateAmount(amount);

        Account sender = getAccount(fromAcc);
        Account receiver = getAccount(toAcc);

        if (sender.getBalance()< amount) {
            throw new IllegalArgumentException("Insufficient balance to transfer");
        }
        validatePassword(sender,password);
 
        sender.setBalance(sender.getBalance()-amount);
        accountRepository.save(sender);
        saveTransaction(sender, "TRANSFER-Successfull", amount);

        // add to receiver
        receiver.setBalance(receiver.getBalance()+amount);
        accountRepository.save(receiver);
        saveTransaction(receiver, "TRANSFER-RECEIVED", amount);
        
        
    }
    
    

    // ------------------- get Account details Methods -------------------
    public Account getAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) throw new IllegalArgumentException("Account not found: " + accountNumber);
        return account;
    }
    
    // ------------------- get Account balance Methods -------------------
    public double getAccountBalance(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) throw new IllegalArgumentException("Account not found: " + accountNumber);
        return account.getBalance();
    }
    
    
    
    // ------------------- Validate  Amount -------------------
    private void validateAmount(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be positive");
    }

    
    
    // ------------------- Validate  Password -------------------
    private void validatePassword( Account account , String password) {
        if (!account.getUser().getPassword().equals(password))
            throw new IllegalArgumentException("Sender password is wornng ");
        
        
    }

    
    // ------------------- Save the transaction -------------------
    
    private void saveTransaction(Account account, String type, double amount) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setType(type);
        transaction.setAmount(amount);
        transactionRepository.save(transaction);
    }
    
    // ------------------- Show the transaction -------------------
    public List<Transaction> getTransactions(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        return transactionRepository.findByAccount(account);
    }
    
    
    
}
