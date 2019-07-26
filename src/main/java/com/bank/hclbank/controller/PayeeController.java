package com.bank.hclbank.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.hclbank.entity.Payee;
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
		Payee addPayee = payeeService.addPayee(payeeRequestModel);
		ResponseData response = new ResponseData("Hi, Payee with account "+payeeRequestModel.getPayeeAccountNumber()+" number requesting, waiting for verification", HttpStatus.OK, addPayee);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}



	@GetMapping("/viewBeneficiaries/{userId}")
	public ResponseEntity<ResponseData> viewBeneficiaries(@PathVariable(value = "userId") Long userId) throws ApplicationException {
		ResponseData response = null;
		List<Payee> activePayeeList;
		activePayeeList = payeeService.viewBeneficiaries(userId);
		if (!ObjectUtils.isEmpty(activePayeeList)) {
			response = new ResponseData("The payees are as follows: ", HttpStatus.OK, activePayeeList);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return null;



	}

	@DeleteMapping("/removePayee")
	public ResponseEntity<ResponseData> removePayee(@RequestParam Long payeeId) throws ApplicationException{

		if(payeeId==null) 
			throw new ApplicationException("Please provide payee Id to whom you want to remove..");

		else{
			Payee payee=payeeService.removePayee(payeeId);
			ResponseData response = new ResponseData("Find below details of payee id :"+payeeId+" who is removed from the system", HttpStatus.OK,payee);
			return new ResponseEntity<ResponseData>(response, HttpStatus.OK);
		}
	}

}
