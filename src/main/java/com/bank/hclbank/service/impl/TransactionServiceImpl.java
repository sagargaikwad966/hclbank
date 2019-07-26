package com.bank.hclbank.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bank.hclbank.entity.Account;
import com.bank.hclbank.entity.Transaction;
import com.bank.hclbank.repository.TransactionRepository;
import com.bank.hclbank.service.AccountService;
import com.bank.hclbank.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService
{
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	AccountService accountService;
	
	
	public List<Transaction> getTransactionByAccount(Account account)
	{
		Pageable sortedByTransactionTime = PageRequest.of(0,3,Sort.by("transactionTime").descending());
		
		return transactionRepository.findByAccountNumber(account.getAccountNumber(),sortedByTransactionTime);
	}


	@Transactional
	public String fundTransfer(Long fromAccountNumber, Long toAccountNumber, Double transferAmount) {
		
		Account fromAccount = accountService.getAccountByAccountNumber(fromAccountNumber);
		
		Account toAccount = accountService.getAccountByAccountNumber(toAccountNumber);
		
		Transaction creditTransaction = new Transaction();
		creditTransaction.setTransactionType("credit");
		creditTransaction.setAccountNumber(toAccountNumber);
		creditTransaction.setBalance(toAccount.getBalance() + transferAmount);
		creditTransaction.setTransactionAmount(transferAmount);
		creditTransaction.setTransactionTime(new Date());
		
		transactionRepository.save(creditTransaction);
		
		Transaction debitTransaction = new Transaction();
		debitTransaction.setTransactionType("debit");
		debitTransaction.setAccountNumber(fromAccountNumber);
		debitTransaction.setBalance(fromAccount.getBalance() - transferAmount);
		debitTransaction.setTransactionAmount(transferAmount);
		debitTransaction.setTransactionTime(new Date());
		
		transactionRepository.save(debitTransaction);

		fromAccount.setBalance(fromAccount.getBalance() - transferAmount);
		accountService.updateAccount(fromAccount);
		
		toAccount.setBalance(toAccount.getBalance() + transferAmount);
		accountService.updateAccount(toAccount);
		
		return creditTransaction + "\n" + debitTransaction;
	}

}
