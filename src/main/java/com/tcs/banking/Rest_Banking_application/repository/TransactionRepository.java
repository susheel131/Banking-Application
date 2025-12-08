package com.tcs.banking.Rest_Banking_application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.banking.Rest_Banking_application.entity.Account;
import com.tcs.banking.Rest_Banking_application.entity.Transaction;

public interface TransactionRepository  extends JpaRepository<Transaction, Long>{

	List<Transaction> findByAccount(Account account);

}
