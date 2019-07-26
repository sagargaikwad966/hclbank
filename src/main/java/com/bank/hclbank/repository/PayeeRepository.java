package com.bank.hclbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.hclbank.entity.Payee;

@Repository
public interface PayeeRepository extends JpaRepository<Payee, Long>{

}
