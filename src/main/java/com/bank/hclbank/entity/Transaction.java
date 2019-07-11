package com.bank.hclbank.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transaction")
public class Transaction 
{
	@Id
	@Column(name="transaction_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	
	@Column(name="transaction_time", nullable = false)
	private Date transactionTime;
	
	@Column(name="account_number", nullable = false)
	private Long accountNumber;
	
	@Column(name="transaction_type", nullable = false)
	private String transactionType;
	
	@Column(name="transaction_amount", nullable = false)
	private Double transactionAmount;
	
	@Column(name="balance", nullable = false)
	private Double balance;

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Date getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", transactionTime=" + transactionTime
				+ ", accountNumber=" + accountNumber + ", transactionType=" + transactionType + ", transactionAmount="
				+ transactionAmount + ", balance=" + balance + "]";
	}
	
	
	

}
