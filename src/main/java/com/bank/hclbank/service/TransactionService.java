package com.bank.hclbank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.hclbank.entity.Account;
import com.bank.hclbank.entity.Transaction;
import com.bank.hclbank.exception.ApplicationException;

@Service
public interface TransactionService 
{
	public List<Transaction> getTransactionByAccount(Account account);
	public String fundTransfer(Long fromAccountNumber, Long toAccountNumber, Double transferAmount) throws ApplicationException;
	
}
