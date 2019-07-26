package com.bank.hclbank.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.hclbank.entity.Account;
import com.bank.hclbank.entity.User;

@Service
public interface AccountService
{
	public Account createAccount(Account account) throws SQLException;
	public void updateAccount(Account account);
	public Account getAccountByUserId(User user);
	public List<Account> getAllPayeeAccount(Long accountNumber) throws Exception;
	public Account getAccountByAccountNumber(Long fromAccountNumber);
	public Long generateAccountNumber();
	
}
