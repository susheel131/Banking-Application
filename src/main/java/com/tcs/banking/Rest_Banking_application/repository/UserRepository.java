package com.tcs.banking.Rest_Banking_application.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.banking.Rest_Banking_application.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>{
	User findByEmail(String email);
}
