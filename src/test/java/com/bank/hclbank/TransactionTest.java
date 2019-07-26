package com.bank.hclbank;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.bank.hclbank.exception.ApplicationException;

import com.bank.hclbank.entity.Account;
import com.bank.hclbank.entity.Payee;
import com.bank.hclbank.entity.User;
import com.bank.hclbank.model.PayeeRequestModel;
import com.bank.hclbank.model.ResponseData;
import com.bank.hclbank.service.AccountService;
import com.bank.hclbank.service.PayeeService;
import com.bank.hclbank.service.TransactionService;
import com.bank.hclbank.service.impl.TransactionServiceImpl;

import ch.qos.logback.core.boolex.Matcher;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTest {
	
	@Mock
	TransactionService transactionServiceMock;
	
	Payee payee2;
	Payee payee1;
	Account account1,account2;
	User user1,user2;
	
	@Before
	public void setup() {
	payee2=new Payee();
	payee2.setPayeeAccountNumber(1010L);
	payee2.setPayeeId(1L);
	payee2.setPayerAccountNumber(1020L);
	payee2.setStatus("active");
	
	payee1=new Payee();
	payee1.setPayeeAccountNumber(1020L);
	payee1.setPayeeId(2L);
	payee1.setPayerAccountNumber(1010L);
	payee1.setStatus("inactive");	
	
	user1 = new User();
	user2 = new User();
	user1.setUserId(1L);
	user2.setUserId(2L);
	
	account1 = new Account();
	account1.setAccountId(1L);
	account1.setBalance(100D);
	account1.setAccountNumber(1L);
	account1.setUser(user1);
	

	account2 = new Account();
	account2.setAccountId(2L);
	account2.setBalance(200D);
	account2.setAccountNumber(2L);
	account2.setUser(user2);
	
	}
	
@Test
public void fundTransferTest() throws ApplicationException {
	 when(transactionServiceMock.fundTransfer(Matchers.any(Long.class),Matchers.any(Long.class),Matchers.any(Double.class))).thenReturn(new ResponseEntity<String>("sucess",null,HttpStatus.OK));
     ResponseEntity<ResponseData> response =transactionServiceMock.fundTransfer(1L,2L,10D);
		assertNotNull(response);
		
}

@Test
public void fundTransferTestVoid() throws ApplicationException {
	 when(transactionServiceMock.fundTransfer(Matchers.any(Long.class),Matchers.any(Long.class),Matchers.any(Double.class))).thenReturn(new ResponseEntity<String>("sucess",null,HttpStatus.EXPECTATION_FAILED));
     ResponseEntity<ResponseData> response =transactionServiceMock.fundTransfer(2L,1L,10D);
		assertNotNull(response);
		
}
	
	
}
