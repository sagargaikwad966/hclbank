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
	PayeeService payeeService;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	PayeeRepository payeeRepository;


	public Payee addPayee(PayeeRequestModel payeeRequestModel) throws ApplicationException {
		List<Payee> payeeList = new ArrayList<>();
		Payee requestedPayee = new Payee();

		Account payerAccount = accountService.getAccountByAccountNumber(payeeRequestModel.getPayerAccountNumber());

		Account payeeAccount = accountService.getAccountByAccountNumber(payeeRequestModel.getPayeeAccountNumber());

		Optional<List<Payee>> payeeListOptional = payeeRepository
				.findByPayerAccountNumberAndStatus(payerAccount.getAccountNumber(), "ACTIVE");

		boolean isOptionalPresent = payeeListOptional.isPresent();

		if (isOptionalPresent) {
			payeeList = payeeListOptional.get();

			boolean isPayee = payeeList.stream()
					.anyMatch(p -> p.getPayeeAccountNumber().equals(payeeRequestModel.getPayeeAccountNumber()));
			if (isPayee)
				throw new ApplicationException("The Payee with account " + payeeRequestModel.getPayeeAccountNumber()
				+ " number already present");
			else {

				requestedPayee.setPayeeAccountNumber(payeeRequestModel.getPayeeAccountNumber());
				requestedPayee.setPayerAccountNumber(payeeRequestModel.getPayerAccountNumber());
				requestedPayee.setStatus("PENDING");
				payeeRepository.save(requestedPayee);

			}
		} else {
			requestedPayee.setPayeeAccountNumber(payeeRequestModel.getPayeeAccountNumber());
			requestedPayee.setPayerAccountNumber(payeeRequestModel.getPayerAccountNumber());
			requestedPayee.setStatus("PENDING");
			payeeRepository.save(requestedPayee);

		}
		return requestedPayee;
	}



	@Override
	public Payee removePayee(Long payeeId) throws ApplicationException {
		logger.info("In remove Payee");
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
		logger.info("Exit from remove Payee");
		return payee;

	}
	/**
	 * @param userId
	 * @return
	 * @throws ApplicationException 
	 */
	public List<Payee> viewBeneficiaries(Long userId) throws ApplicationException{

		Account account = accountRepository.getAccountForUser(userId);

		if(!ObjectUtils.isEmpty(account)) {
			List<Payee> payeeList = payeeRepository.getPayeesList(account.getAccountNumber(), "active");
			if(!ObjectUtils.isEmpty(payeeList)) {
				return payeeList;
			} else {
				throw new ApplicationException("No Payees are added yet.");
			}
		} else 
			throw new ApplicationException("User is invalid.");
	}
}