package com.bank.hclbank.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.bank.hclbank.entity.User;

@Entity
@Table(name="account")
public class Account 
{
	@Id
	@Column(name="account_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	
	@Column(name="account_number", unique=true, nullable = false)
	private Long accountNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
	private User user;
	
	@Column(name="balance", nullable = false)
	private Double balance;

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Account(Long accountNumber, User user, Double balance) {
		super();
		this.accountNumber = accountNumber;
		this.user = user;
		this.balance = balance;
	}

	public Account() {
		super();
	}
	
	

}
