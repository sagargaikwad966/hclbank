package com.bank.hclbank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.hclbank.exception.ApplicationException;
import com.bank.hclbank.model.PayeeRequestModel;
import com.bank.hclbank.entity.Payee;

@Service
public interface PayeeService {

	void addPayee(PayeeRequestModel payeeRequestModel) throws ApplicationException;

	public List<Payee> viewBeneficiaries(Long userId);
}
