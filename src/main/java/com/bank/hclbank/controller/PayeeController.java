package com.bank.hclbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.hclbank.exception.ApplicationException;
import com.bank.hclbank.model.PayeeRequestModel;
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
	
	

}
