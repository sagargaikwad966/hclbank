package com.bank.hclbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.hclbank.entity.Account;
import com.bank.hclbank.entity.User;
import com.bank.hclbank.exception.InvalidUserDataException;
import com.bank.hclbank.service.UserService;

@RestController
@RequestMapping("/registration")
public class RegistrationController 
{
	@Autowired
	UserService userService;
	
	/**
	 * This method is used to register user in the application.
	 * @param user to register user for requested information
	 * @return Account This returns Account object for the register user for successful registration
	 * @return String This returns Error message for unsuccessful registration
	 */	
	/*
	 * @PostMapping("/register") public ResponseEntity<?> registerUser(@RequestBody
	 * User user) { Account userAccount = null;
	 * 
	 * try { checkUser(user); } catch(InvalidUserDataException e) { return new
	 * ResponseEntity<String>("Invalid User Data : "+
	 * e.getCause().getMessage(),HttpStatus.BAD_REQUEST); }
	 * 
	 * try { userAccount = userService.registerUser(user); } catch(Exception e) {
	 * return new
	 * ResponseEntity<String>("Invalid User Data : "+e.getCause().getMessage(),
	 * HttpStatus.BAD_REQUEST); } return new ResponseEntity<Account>(userAccount,
	 * HttpStatus.OK); }
	 */
	
	/**
	 * This method is used to check user data is valid otherwise throwing InvalidUserDataException
	 * @param user to check user before registration
	 */	

	public void checkUser(User user) throws InvalidUserDataException
	{
		if(ObjectUtils.isEmpty(user)) 
			throw new InvalidUserDataException("Invalid User ");
		if(StringUtils.isEmpty(user.getUserName()))
			throw new InvalidUserDataException("Mandatory element missing : userName");
		if(StringUtils.isEmpty(user.getPassword()) && (user.getPassword().length() < 8))
			throw new InvalidUserDataException("Mandatory element missing : password");			
	}

}
