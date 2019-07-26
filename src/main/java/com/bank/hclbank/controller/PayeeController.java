package com.bank.hclbank.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.hclbank.entity.Payee;
import com.bank.hclbank.exception.ApplicationException;
import com.bank.hclbank.model.PayeeRequestModel;
import com.bank.hclbank.model.ResponseData;
import com.bank.hclbank.service.PayeeService;
import com.bank.hclbank.utils.CommunicationUtils;

@RestController
@RequestMapping("/payee")
public class PayeeController {

	@Autowired
	PayeeService payeeService;

	@Autowired
	CommunicationUtils communicationUtils;

	@PostMapping("/add")
	public ResponseEntity<ResponseData> addPayee(@RequestBody PayeeRequestModel payeeRequestModel)
			throws ApplicationException, NoSuchAlgorithmException {
		Payee addPayee = payeeService.addPayee(payeeRequestModel);
		
		payeeService.generateOtpForPayerAndSendMail(addPayee.getPayeeId());
		ResponseData response = new ResponseData("Hi, Payee with account " + payeeRequestModel.getPayeeAccountNumber()
				+ " number requesting, waiting for verification", HttpStatus.OK, addPayee);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/viewBeneficiaries/{userId}")
	public ResponseEntity<ResponseData> viewBeneficiaries(@PathVariable(value = "userId") Long userId)
			throws ApplicationException {
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
	public ResponseEntity<ResponseData> removePayee(@RequestParam Long payeeId) throws ApplicationException, NoSuchAlgorithmException {

		if (payeeId == null)
			throw new ApplicationException("Please provide payee Id to whom you want to remove..");

		else {
			Payee payee = payeeService.removePayee(payeeId);
			ResponseData response = new ResponseData(
					"Find below details of payee id :" + payeeId + " who is removed from the system", HttpStatus.OK,
					payee);
			payeeService.generateOtpForPayerAndSendMail(payee.getPayeeId());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PutMapping("/validate/{otp}/{id}")
	public ResponseEntity<ResponseData> validateOtp(@RequestParam("otp") Integer otp, @RequestParam("id") Long payeeid)
			throws ApplicationException, ExecutionException {
		Payee payee = payeeService.getPayeeById(payeeid);

		String isValidOTP = communicationUtils.processValidOtp(otp, payeeid);

		ResponseData response = new ResponseData();
		if (isValidOTP.equalsIgnoreCase("SUCCESS")) {
			if (payee.getStatus().equalsIgnoreCase("PENDING")) {
				payee.setStatus("ACTIVE");
				payeeService.updatePayee(payee);
				response = new ResponseData("Hi, Payee added successfully", HttpStatus.OK, payee);
			}
			if (payee.getStatus().equalsIgnoreCase("PENDING DELETE")) {
				payee.setStatus("INACTIVE");
				payeeService.updatePayee(payee);
				response = new ResponseData("Hi, Payee deleted successfully", HttpStatus.OK, payee);
			}

		} else {
			throw new ApplicationException("Invalid OTP");
		}

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
