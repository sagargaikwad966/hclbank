package com.bank.hclbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.hclbank.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUserNameAndPassword(String userName, String password);
	

}
