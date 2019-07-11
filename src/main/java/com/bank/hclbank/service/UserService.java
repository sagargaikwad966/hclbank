package com.bank.hclbank.service;

import java.util.Random;
import java.util.List;

import javax.persistence.SecondaryTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.bank.hclbank.entity.Account;
import com.bank.hclbank.entity.Transaction;
import com.bank.hclbank.entity.User;
import com.bank.hclbank.repository.AccountRepository;
import com.bank.hclbank.repository.UserRepository;



@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	TransactionService transactionService;
	
	
	public User createUser(User user) throws Exception
	{
		return userRepository.save(user);
	}
	
	public Account registerUser(User user) throws Exception 
	{
		if(ObjectUtils.isEmpty(createUser(user)))
		{
			throw new Exception("User is Not Created successfully : ");	
		}
		
		Account account = new Account();
		account.setBalance(10000.00);
		account.setUser(user);
		account.setAccountNumber(accountService.generateAccountNumber());
		
		return accountService.createAccount(account);
	}

	public List<Transaction> validateUser(String userName, String password) throws Exception
	{
		User loginUser = userRepository.findByUserNameAndPassword(userName, password);
		
		if(ObjectUtils.isEmpty(loginUser))
		{
			throw new Exception("Please enter the valid credential");
		}
		
		Account loginAccount = accountService.getAccountByUserId(loginUser);
		
		if(ObjectUtils.isEmpty(loginAccount))
		{
			throw new Exception("The Account is not found for the "+loginUser.getUserName());
		}

		return transactionService.getTransactionByAccount(loginAccount);

	}

	

	public List<User> getAllUser() {
		return userRepository.findAll();
	}



}
