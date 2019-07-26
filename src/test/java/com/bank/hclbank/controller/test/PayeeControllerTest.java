package com.bank.hclbank.controller.test;

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
import org.springframework.http.ResponseEntity;

import com.bank.hclbank.controller.PayeeController;
import com.bank.hclbank.entity.Payee;
import com.bank.hclbank.exception.ApplicationException;
import com.bank.hclbank.model.ResponseData;
import com.bank.hclbank.service.PayeeService;

@RunWith(MockitoJUnitRunner.class)
public class PayeeControllerTest {

	@InjectMocks
	PayeeController payeeControllerMock;

	@Mock
	PayeeService payeeServiceMock;

	@Test
	public void viewBeneficiaries() throws ApplicationException {
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

}
