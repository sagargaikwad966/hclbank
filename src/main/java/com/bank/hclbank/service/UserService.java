package com.bank.hclbank.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.hclbank.entity.Account;
import com.bank.hclbank.entity.Transaction;
import com.bank.hclbank.entity.User;
import com.bank.hclbank.exception.InvalidUserDataException;



@Service
public interface UserService {
	
	public Account registerUser(User user) throws SQLException, InvalidUserDataException;
	public List<Transaction> validateUser(String userName, String password) throws Exception;
	public List<User> getAllUser();

	

}
