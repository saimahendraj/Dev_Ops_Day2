package org.cap.bankapp.test;

import static org.junit.Assert.*;

import org.cap.dao.AccountDao;
import org.cap.dto.Account;
import org.cap.dto.Address;
import org.cap.dto.Customer;
import org.cap.exception.InvalidInitialAmountException;
import org.cap.service.AcccountService;
import org.cap.service.AccountServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class BankAppAddNumTestCase {

	
	private AcccountService acccountService;
	
	@Mock
	private AccountDao acccountDao;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		acccountService = new AccountServiceImpl(acccountDao);
	}
	
	@After
	public void tearDown(){
		/*MockitoAnnotations.initMocks(this);
		acccountService = new AccountServiceImpl(acccountDao);*/
	}
	
	@Test
	public void test_addnumbers() {
		
		//acccountService = new AccountServiceImpl();
		assertEquals(50, acccountService.addNumbers(20, 30));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_when_customer_is_null() throws InvalidInitialAmountException {
		
		//acccountService = new AccountServiceImpl();
		Customer customer = null;
		acccountService.addAccount(customer, 10000);
	}
	
	@Test(expected = InvalidInitialAmountException.class)
	public void test_with_invalid_initial_amount() throws InvalidInitialAmountException {
		
		//acccountService = new AccountServiceImpl();
		Customer customer = new Customer();
		customer.setCustName("Sai");
		Address address= new Address();
		address.setAddressLine("Chennai");
		customer.setCustAddress(address);
		acccountService.addAccount(customer, 100);
	}
	
	@Test
	public void test_add_account_with_valid_customer() throws InvalidInitialAmountException{
		Customer customer = new Customer();
		customer.setCustName("Sai");
		Address address= new Address();
		address.setAddressLine("Chennai");
		customer.setCustAddress(address);
		Account account = new Account();
		account.setCustomer(customer);
		account.setAmount(6000);
		
		//Declaration
		Mockito.when(acccountDao.createAccount(account)).thenReturn(true);
		
		//Actual logic
		Account new_account = acccountService.addAccount(customer, 6000);
		
		//Verification
		Mockito.verify(acccountDao).createAccount(account);
		
		//assertEquals(account, new_account);
		assertEquals(6000, new_account.getAmount(),0.0);
		
	}
	

}
