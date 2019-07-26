package com.bank.hclbank.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Data
@Table(name="user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5348159924822197319L;

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(name="user_name", unique=true, nullable = false)
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="phone", unique=true, nullable = false)
	private Long phone;
	
	@Column(name="email", unique=true)
	private String email;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="dob")
	private LocalDate dob;
	

}
