package com.bank.hclbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.hclbank.entity.User;
import com.bank.hclbank.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	UserService userService;
	
	@GetMapping("/all")
	public List<User> getAllUser()
	{
		return userService.getAllUser();
	}

}
