package com.bank.hclbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.hclbank.exception.ApplicationException;
import com.bank.hclbank.model.PayeeRequestModel;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.hclbank.entity.Payee;
import com.bank.hclbank.exception.ApplicationException;
import com.bank.hclbank.model.ResponseData;
import com.bank.hclbank.service.PayeeService;


@RestController
@RequestMapping("/payee")
public class PayeeController 
{
	
	@Autowired
	PayeeService payeeService;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@PostMapping("/add")
	public ResponseEntity<ResponseData> addPayee(@RequestBody PayeeRequestModel payeeRequestModel) throws ApplicationException
	{
		
		payeeService.addPayee(payeeRequestModel);
		return null;
		
	}
	
	


	@GetMapping("/viewBeneficiaries/{userId}")
	public ResponseEntity<ResponseData> viewBeneficiaries(@PathVariable(value = "userId") Long userId) throws ApplicationException {
		ResponseData response = null;
		List<Payee> activePayeeList = payeeService.viewBeneficiaries(userId);
		if (!ObjectUtils.isEmpty(activePayeeList)) {
			response = new ResponseData("The payees are as follows: ", HttpStatus.OK, activePayeeList);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		throw new ApplicationException();

	}


}
