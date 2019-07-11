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

import com.bank.hclbank.entity.Account;
import com.bank.hclbank.service.AccountService;
import com.bank.hclbank.service.TransactionService;

@RestController
@RequestMapping("/fundTransfer")
public class TransactionController 
{
	@Autowired
	AccountService accountService;
	
	@Autowired
	TransactionService transactionService;
	
	@PostMapping("/getPayee")
	public ResponseEntity<?> getAllPayeeAccount(@RequestParam(value="accountNumber") Long accountNumber)
	{
		List<Account> accountList;
		try
		{
			accountList = accountService.getAllPayeeAccount(accountNumber);
			
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>("Invalid request : "+e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Account>>(accountList, HttpStatus.OK);
	}
	
	@PostMapping("/transfer")
	public String fundTransfer(@RequestParam(value="fromAccountNumber") Long fromAccountNumber, @RequestParam(value="toAccountNumber") Long toAccountNumber, @RequestParam(value="transferAmount") Double transferAmount)
	{
		return transactionService.fundTransfer(fromAccountNumber, toAccountNumber, transferAmount);
	}

}
