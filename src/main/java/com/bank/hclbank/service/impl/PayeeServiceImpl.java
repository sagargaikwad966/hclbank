package com.bank.hclbank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.hclbank.entity.Account;
import com.bank.hclbank.exception.ApplicationException;
import com.bank.hclbank.model.PayeeRequestModel;
import com.bank.hclbank.service.AccountService;
import com.bank.hclbank.service.PayeeService;

@Service
public class PayeeServiceImpl implements PayeeService {

	@Autowired
	AccountService accountService;
	
	
	@Override
	public void addPayee(PayeeRequestModel payeeRequestModel) throws ApplicationException 
	{
		Account payerAccount = accountService.getAccountByAccountNumber(payeeRequestModel.getPayerAccountNumber());
		
		Account payeeeAccount = accountService.getAccountByAccountNumber(payeeRequestModel.getPayeeAccountNumber());
		
		
	}

}
