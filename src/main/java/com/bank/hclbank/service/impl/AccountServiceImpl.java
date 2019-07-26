package com.bank.hclbank.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.hclbank.entity.Account;
import com.bank.hclbank.entity.User;
import com.bank.hclbank.repository.AccountRepository;
import com.bank.hclbank.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService
{
	@Autowired
	AccountRepository accountRepository;
	
	public Account createAccount(Account account) throws SQLException
	{
		return accountRepository.save(account);
	}
	
	public void updateAccount(Account account)
	{
		accountRepository.save(account);
	}
	
	public Account getAccountByUserId(User user)
	{
		return accountRepository.findByUser(user);
	}

	public List<Account> getAllPayeeAccount(Long accountNumber) throws Exception
	{
		// TODO Auto-generated method stub
		return accountRepository.findByAccountNumberNotIn(accountNumber);
	}

	public Account getAccountByAccountNumber(Long fromAccountNumber) {
		// TODO Auto-generated method stub
		return accountRepository.findByAccountNumber(fromAccountNumber);
	}

	public Long generateAccountNumber() {

		return new Random().nextLong();
	}

}
