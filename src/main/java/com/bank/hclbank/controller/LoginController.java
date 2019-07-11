package com.bank.hclbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
			return new ResponseEntity<String>("Invalid Credential : "+e.getMessage(),HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<List<Transaction>>(lastFiveTransactions,HttpStatus.OK);
	}
	

}
