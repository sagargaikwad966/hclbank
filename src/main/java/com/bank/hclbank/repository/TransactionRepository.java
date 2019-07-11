package com.bank.hclbank.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.hclbank.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>
{
	public List<Transaction> findByAccountNumber(Long accountNumber, Pageable page);
}
