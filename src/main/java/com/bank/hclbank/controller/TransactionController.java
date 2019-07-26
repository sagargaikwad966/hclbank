package com.bank.hclbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.hclbank.entity.Account;
import com.bank.hclbank.exception.ApplicationException;
import com.bank.hclbank.model.ResponseData;
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
	
	
	/**
	 * This method is used to get all payee list for given user account number
	 * @param accountNumber to fetch the all payee for this account number
	 * @return List<Account> This returns List of all Payee Accounts
	 * @throws Exception 
	 */	
	@PostMapping("/getPayee")
	public ResponseEntity<ResponseData> getAllPayeeAccount(@RequestParam(value="accountNumber") Long accountNumber) throws Exception
	{
			if(accountNumber.equals(null))	
				throw new ApplicationException("Please provide account number");
			else {
		      List<Account>	accountList = accountService.getAllPayeeAccount(accountNumber);
		      if(!accountList.isEmpty()) {
			    ResponseData response = new ResponseData("Please find below list of payees:", HttpStatus.OK,accountList);
			    return new ResponseEntity<>(response,HttpStatus.OK);
		      }
		      else 
		    	  throw new ApplicationException("There is no payee for this account number");
		}
				
	}
	
	/**
	 * This method is used to transfer the fund 
	 * @param fromAccountNumber to debit fund from this Account
	 * @param toAccountNumber to credit fund to this Account
	 * @param transferAmount This is the transfer amount
	 * @return String This returns message for successful / unsuccessful transaction
	 * @throws ApplicationException 
	 */	
	@PostMapping("/transfer")
	public ResponseEntity fundTransfer(@RequestParam(value="fromAccountNumber") Long fromAccountNumber, @RequestParam(value="toAccountNumber") Long toAccountNumber, @RequestParam(value="transferAmount") Double transferAmount) throws ApplicationException
	{
		try {
			return transactionService.fundTransfer(fromAccountNumber, toAccountNumber, transferAmount);
		} catch (ApplicationException e) {

			return new ResponseEntity<String>("Invalid request : "+e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
}
