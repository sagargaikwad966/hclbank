package com.bank.hclbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.hclbank.entity.Transaction;
import com.bank.hclbank.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginController 
{
	@Autowired
	UserService userService;

	/**
	 * This method is used to get user which is existing in the application.
	 * @param userName to get User for particular credential
	 * @param password to get User for particular credential
	 * @return User This returns user object for the given Username & Password
	 */
	@GetMapping("/user")
	public ResponseEntity<?> validateUser(@RequestParam(value="userName") String userName, @RequestParam(value="password") String password)
	{
		List<Transaction> lastFiveTransactions = null;
		try
		{
			lastFiveTransactions = userService.validateUser(userName,password);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>("Invalid Credential : "+e.getMessage(),HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(lastFiveTransactions,HttpStatus.OK);
	}
	

}
