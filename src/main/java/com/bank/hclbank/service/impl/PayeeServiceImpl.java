package com.bank.hclbank.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.bank.hclbank.entity.Account;
import com.bank.hclbank.entity.Payee;
import com.bank.hclbank.exception.ApplicationException;
import com.bank.hclbank.model.PayeeRequestModel;
import com.bank.hclbank.repository.AccountRepository;
import com.bank.hclbank.repository.PayeeRepository;
import com.bank.hclbank.service.AccountService;
import com.bank.hclbank.service.PayeeService;

@Service
public class PayeeServiceImpl implements PayeeService 
{

	private static final Logger logger = LoggerFactory.getLogger(PayeeServiceImpl.class);


	@Autowired
	AccountService accountService;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	PayeeRepository payeeRepository;

	@Override
	public void addPayee(PayeeRequestModel payeeRequestModel) throws ApplicationException 
	{
		Account payerAccount = accountService.getAccountByAccountNumber(payeeRequestModel.getPayerAccountNumber());

		Account payeeeAccount = accountService.getAccountByAccountNumber(payeeRequestModel.getPayeeAccountNumber());
		List<Payee> payeeList = null;

		System.out.println(payeeList);

		/*
		 * boolean isPayee = payeeList.stream().anyMatch(p ->
		 * p.getPayeeAccount().getAccountNumber().equals(payeeRequestModel.
		 * getPayeeAccountNumber()));
		 * 
		 * logger.debug("IsPresent"+ isPayee); System.out.println(isPayee);
		 */


	}

	/**
	 * @param userId
	 * @return
	 */
	public List<Payee> viewBeneficiaries(Long userId){
		List<Payee> activePayeeList = new ArrayList<Payee>();

		Optional<Account> account = accountRepository.findById(userId);

		if(!ObjectUtils.isEmpty(account)) {
			List<Payee> payeeList = account.get().getPayeeList();
			for(Payee payee : payeeList) {
				if(payee.getStatus().equalsIgnoreCase("active")) {
					activePayeeList.add(payee);
				}
			}
		}
		return activePayeeList;
	}

	@Override
	public Payee removePayee(Long payeeId) throws ApplicationException {
		Payee payee=new Payee();
		Optional<Payee> payeeList= payeeRepository.findById(payeeId);

		if(payeeList.isPresent()) {
			payee=payeeList.get();

			if(payee.getStatus().equals("inactive"))
				throw new ApplicationException("Given Payee Id:"+payeeId+" is already removed");
			else {
				payee.setStatus("inactive");
			}
		}
		else throw new ApplicationException("Please provide valid payee Id to be removed");

		return payee;

	}

}		

