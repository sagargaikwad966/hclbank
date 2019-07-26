package com.bank.hclbank.service;

import org.springframework.stereotype.Service;

import com.bank.hclbank.exception.ApplicationException;
import com.bank.hclbank.model.PayeeRequestModel;

@Service
public interface PayeeService {

	void addPayee(PayeeRequestModel payeeRequestModel) throws ApplicationException;

}
