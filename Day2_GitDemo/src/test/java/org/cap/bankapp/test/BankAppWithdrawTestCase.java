package org.cap.bankapp.test;

import static org.junit.Assert.*;

import org.cap.dao.AccountDao;
import org.cap.dto.Account;
import org.cap.dto.Address;
import org.cap.dto.Customer;
import org.cap.exception.InsufficientBalanceException;
import org.cap.exception.InvalidInitialAmountException;
import org.cap.service.AcccountService;
import org.cap.service.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BankAppWithdrawTestCase {

	private AcccountService acccountService;
	
	@Mock
	private AccountDao acccountDao;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		acccountService = new AccountServiceImpl(acccountDao);
	}
	
	@Test(expected = InsufficientBalanceException.class)
	public void test_with_insuffucientBalance_exception() throws InsufficientBalanceException {
		
		Address address= new Address();
		address.setAddressLine("Chennai");
		
		Customer customer = new Customer();
		customer.setCustName("Sai");
		customer.setCustAddress(address);
		
		Account account = new Account();
		account.setCustomer(customer);
		account.setAmount(6000);
		account.setAccountNo(12);
		//Declaration
		Mockito.when(acccountDao.findAccountById(12)).thenReturn(account);
		
		//Actual logic
		acccountService.withdraw(12, 7000);
		
	}
	
	@Test
	public void test_while_withdraw() throws InsufficientBalanceException {
		
		Address address= new Address();
		address.setAddressLine("Chennai");
		
		Customer customer = new Customer();
		customer.setCustName("Sai");
		customer.setCustAddress(address);
		
		Account account = new Account();
		account.setCustomer(customer);
		account.setAmount(6000);
		account.setAccountNo(12);
		
		//Declaration
		Mockito.when(acccountDao.findAccountById(12)).thenReturn(account);
		
		//Actual logic
		Account update_account = acccountService.withdraw(12, 5000);
		
		//Verify
		Mockito.verify(acccountDao).findAccountById(12);
		
		//assertEquals(account, new_account);
		assertEquals(1000, update_account.getAmount(),0.0);
	}
	
	@Test
	public void test_while_deposit() {
		
		Customer customer = new Customer();
		customer.setCustName("Sai");
		Address address= new Address();
		address.setAddressLine("Chennai");
		customer.setCustAddress(address);
		Account account = new Account();
		account.setCustomer(customer);
		account.setAmount(6000);
		int accountNo = 12;
		//Declaration
		Mockito.when(acccountDao.findAccountById(accountNo)).thenReturn(account);
		
		//Actual logic
		Account new_account = acccountService.deposit(accountNo, 5000);
		
		//assertEquals(account, new_account);
		assertEquals(11000, new_account.getAmount(),0.0);
	}

}
