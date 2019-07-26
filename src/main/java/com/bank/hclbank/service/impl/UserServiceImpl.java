package com.bank.hclbank.service.impl;

import java.util.Random;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.SecondaryTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.bank.hclbank.entity.Account;
import com.bank.hclbank.entity.Transaction;
import com.bank.hclbank.entity.User;
import com.bank.hclbank.exception.InvalidUserDataException;
import com.bank.hclbank.repository.AccountRepository;
import com.bank.hclbank.repository.UserRepository;
import com.bank.hclbank.service.AccountService;
import com.bank.hclbank.service.TransactionService;
import com.bank.hclbank.service.UserService;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	TransactionService transactionService;
	
	/**
	 * This method is used to save user entry in database table user
	 * @param user to save user entry
	 * @return User This return User Object which successfully insert
	 */
	public User createUser(User user) throws SQLException
	{
		return userRepository.save(user);
	}
	
	/**
	 * This method is used to register user and create Bank Account for the user
	 * @param user to create Account
	 * @return Account This return Account Object for given user
	 */
	public Account registerUser(User user) throws SQLException, InvalidUserDataException 
	{
		if(ObjectUtils.isEmpty(createUser(user)))
		{
			throw new InvalidUserDataException("User is Not Created successfully : ");
		}
		
		Account account = new Account();
		account.setBalance(10000.00);
		account.setUser(user);
		account.setAccountNumber(accountService.generateAccountNumber());
		
		return accountService.createAccount(account);
	}

	/**
	 * This method is used to get user which is existing in the application.
	 * @param userName to get User for particular credential
	 * @param password to get User for particular credential
	 * @return User This returns user object for the given Username & Password
	 */
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

	
	/**
	 * This method is used to get user which is existing in the application.
	 * @return List<User> This returns List of users object
	 */
	public List<User> getAllUser() {
		return userRepository.findAll();
	}



}
