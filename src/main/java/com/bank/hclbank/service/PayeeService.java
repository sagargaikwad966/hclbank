package com.bank.hclbank.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.hclbank.entity.Payee;
import com.bank.hclbank.exception.ApplicationException;
import com.bank.hclbank.model.PayeeRequestModel;

@Service
public interface PayeeService {

	public Payee removePayee(Long payeeId) throws ApplicationException;

	public Payee addPayee(PayeeRequestModel payeeRequestModel) throws ApplicationException, NoSuchAlgorithmException;

	public List<Payee> viewBeneficiaries(Long userId) throws ApplicationException;

	public Payee getPayeeById(Long payeeid) throws ApplicationException;

	public Payee updatePayee(Payee payee);
	
	public Boolean generateOtpForPayerAndSendMail(Long payeeId) throws ApplicationException, NoSuchAlgorithmException;
	
}
