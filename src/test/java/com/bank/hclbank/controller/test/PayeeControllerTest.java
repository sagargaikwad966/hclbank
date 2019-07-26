package com.bank.hclbank.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.bank.hclbank.controller.PayeeController;
import com.bank.hclbank.entity.Payee;
import com.bank.hclbank.exception.ApplicationException;
import com.bank.hclbank.model.PayeeRequestModel;
import com.bank.hclbank.model.ResponseData;
import com.bank.hclbank.service.PayeeService;

@RunWith(MockitoJUnitRunner.class)
public class PayeeControllerTest {

	@InjectMocks
	PayeeController payeeControllerMock;

	@Mock
	PayeeService payeeServiceMock;
        
        
	Payee payee;
	Payee payee1;
	
	PayeeRequestModel payeeRequestModel;
	
	@Before
	public void setup() {
	payee=new Payee();
	payee.setPayeeAccountNumber(12345L);
	payee.setPayeeId(1L);
	payee.setPayerAccountNumber(123890L);
	payee.setStatus("active");
	
	payeeRequestModel=new PayeeRequestModel();
	payeeRequestModel.setPayeeAccountNumber(12345L);
	payeeRequestModel.setPayerAccountNumber(123789L);
	
	}

	@Test
	public void testViewBeneficiaries() throws ApplicationException {
		Payee payee = new Payee();
		payee.setPayeeAccountNumber(123123L);
		payee.setPayerAccountNumber(123456L);
		payee.setStatus("active");

		List<Payee> payeeList = new ArrayList<Payee>();
		payeeList.add(payee);
		Mockito.when(payeeServiceMock.viewBeneficiaries(1L)).thenReturn(payeeList);

		ResponseEntity<ResponseData> response = payeeControllerMock.viewBeneficiaries(1L);

		assertNotNull(response);
		assertEquals(200, response.getStatusCodeValue());
	}
        

	@Test
	public void testRemovePayee() throws ApplicationException {
		Long payeeId=1L;
		
		when(payeeServiceMock.removePayee(payeeId)).thenReturn(payee);
		ResponseEntity<ResponseData> response =payeeControllerMock.removePayee(payeeId);
		assertNotNull(response);
		
	}
	@Test(expected = ApplicationException.class)
	public void testRemovePayeeException()throws ApplicationException{
		ResponseEntity<ResponseData> response =payeeControllerMock.removePayee(null);
	}
	
	@Test
	public void testAddPayee() throws ApplicationException {
		
	      when(payeeServiceMock.addPayee(payeeRequestModel)).thenReturn(payee);
	      ResponseEntity<ResponseData> response =payeeControllerMock.addPayee(payeeRequestModel);
			assertNotNull(response);
			
	}

	@Test(expected = ApplicationException.class)
	public void testViewBeneficiariesNegative() throws ApplicationException {

		List<Payee> payeeList = new ArrayList<Payee>();
		Mockito.when(payeeServiceMock.viewBeneficiaries(1L)).thenReturn(payeeList);

		ResponseEntity<ResponseData> response = payeeControllerMock.viewBeneficiaries(1L);

		assertNotNull(response);
		assertEquals(400, response.getStatusCodeValue());
	}
}
