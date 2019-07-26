package com.bank.hclbank.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Data
@Table(name="payee")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "payeeId")
public class Payee 
{
	@Id
	@Column(name="payee_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long payeeId;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "payer_account")
	private Account payerAccount;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "payee_account")
	private Account payeeAccount;
	
	@Column(name="status")
	private String status;

}
