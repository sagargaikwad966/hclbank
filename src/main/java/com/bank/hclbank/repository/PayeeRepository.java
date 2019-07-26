package com.bank.hclbank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.hclbank.entity.Payee;

@Repository
public interface PayeeRepository extends JpaRepository<Payee, Long>{
	
	public Optional<List<Payee>> findByPayerAccountNumberAndStatus(Long payerAccountNumber, String status);

	@Query(value= "Select * from payee where payer_account_number = :accountNumber and status = :status ", nativeQuery=true)
	public List<Payee> getPayeesList(Long accountNumber, String status);

}
