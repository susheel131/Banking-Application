package com.tcs.banking.Rest_Banking_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.banking.Rest_Banking_application.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByAccountNumber(String accountNumber);

}
