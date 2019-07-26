package com.bank.hclbank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.hclbank.entity.Account;
import com.bank.hclbank.entity.User;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	public Account findByUser(User user);
	
	public List<Account> findByAccountNumberNotIn(Long accountNumber);
	
	public Optional<Account> findByAccountNumber(Long accountNumber);

}
