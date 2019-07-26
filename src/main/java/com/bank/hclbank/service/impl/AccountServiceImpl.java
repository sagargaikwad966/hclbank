package com.bank.hclbank.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.hclbank.entity.Account;
import com.bank.hclbank.entity.User;
import com.bank.hclbank.exception.ApplicationException;
import com.bank.hclbank.repository.AccountRepository;
import com.bank.hclbank.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService
{
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Account createAccount(Account account) throws SQLException
	{
		return accountRepository.save(account);
	}
	@Override
	public void updateAccount(Account account)
	{
		accountRepository.save(account);
	}
	@Override
	public Account getAccountByUserId(User user)
	{
		return accountRepository.findByUser(user);
	}
	@Override
	public List<Account> getAllPayeeAccount(Long accountNumber) throws Exception
	{
		return accountRepository.findByAccountNumberNotIn(accountNumber);
	}
	
	@Override
	public Account getAccountByAccountNumber(Long fromAccountNumber) throws ApplicationException 
	{
		Account account = new Account();
		Optional<Account> findByAccountNumberOptional = accountRepository.findByAccountNumber(fromAccountNumber);
		
		boolean isOptionalPresent = findByAccountNumberOptional.isPresent();
		if(isOptionalPresent)
		{
			account = findByAccountNumberOptional.get();
		}
		else
		{
			throw new ApplicationException("Hi, The Account with : "+fromAccountNumber+" number not found");
		}
		return account;
	}
	
	@Override
	public Long generateAccountNumber() {

		return new Random().nextLong();
	}

}
