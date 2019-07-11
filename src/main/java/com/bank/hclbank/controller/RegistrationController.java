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
import com.bank.hclbank.service.UserService;

@RestController
@RequestMapping("/registration")
public class RegistrationController 
{
	@Autowired
	UserService userService;
	
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
		Account userAccount = null;

		try
		{
			checkUser(user);
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>("Invalid User Data : "+e.getMessage(),HttpStatus.BAD_REQUEST);
		}

		try
		{
			userAccount = userService.registerUser(user);
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>("Invalid User Data : "+e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Account>(userAccount, HttpStatus.OK);
	}

	public void checkUser(User user) throws Exception
	{
		if(ObjectUtils.isEmpty(user)) 
			throw new Exception("Invalid User ");
		if(StringUtils.isEmpty(user.getUserName()))
			throw new Exception("Mandatory element missing : userName");
		if(StringUtils.isEmpty(user.getPassword()) && (user.getPassword().length() < 8))
			throw new Exception("Mandatory element missing : password");			
	}

}
