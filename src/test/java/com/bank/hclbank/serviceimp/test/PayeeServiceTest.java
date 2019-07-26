package com.bank.hclbank.serviceimp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.bank.hclbank.entity.Account;
import com.bank.hclbank.entity.Payee;
import com.bank.hclbank.exception.ApplicationException;
import com.bank.hclbank.repository.AccountRepository;
import com.bank.hclbank.repository.PayeeRepository;
import com.bank.hclbank.service.AccountService;
import com.bank.hclbank.service.PayeeService;
import com.bank.hclbank.service.impl.PayeeServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class PayeeServiceTest 
{
	@InjectMocks
	PayeeService payeeService;
	
	@Mock
	AccountService accountServiceMock;
		
	
	@Test
	public void testAddPayee()
	{
		
	}

	@InjectMocks
	PayeeServiceImpl payeeServiceImpl;

	@Mock
	AccountRepository accountRepository;

	@Mock
	PayeeRepository payeeRepository;


	@Test public void viewBeneficiaries() throws ApplicationException {

		Account account = new Account(); account.setAccountNumber(123123L);

		List<Payee> payeeList = new ArrayList<Payee>(); Payee payee = new Payee();
		payee.setPayeeAccountNumber(123456L); payee.setPayerAccountNumber(123123L);
		payee.setStatus("active");

		payeeList.add(payee);

		Mockito.when(accountRepository.getAccountForUser(1L)).thenReturn(account);
		Mockito.when(payeeRepository.getPayeesList(account.getAccountNumber(),
				"active")).thenReturn(payeeList);

		List<Payee> activePayeeList = payeeServiceImpl.viewBeneficiaries(1L);
		assertNotNull(activePayeeList); assertEquals("active",
				activePayeeList.get(0).getStatus()); }

}
