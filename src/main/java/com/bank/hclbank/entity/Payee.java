package com.bank.hclbank.entity;

import java.io.Serializable;

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
@Table(name="payee")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "payeeId")
public class Payee implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7538704451056565210L;

	@Id
	@Column(name="payee_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long payeeId;
	
	@Column(name = "payer_account_number")
	private Long payerAccountNumber;
	
	@Column(name = "payee_account_number")
	private Long payeeAccountNumber;
	
	@Column(name="status")
	private String status;

}
