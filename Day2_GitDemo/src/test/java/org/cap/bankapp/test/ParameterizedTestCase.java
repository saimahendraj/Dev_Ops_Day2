package org.cap.bankapp.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.cap.service.AcccountService;
import org.cap.service.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParameterizedTestCase {

	private int input1;
	private int input2;
	private int output;
	
	private AcccountService accountService;
	
	public ParameterizedTestCase(int input1, int input2, int output) {
		super();
		this.input1 = input1;
		this.input2 = input2;
		this.output = output;
	}
	
	@Parameters
	public static List<Object[]> myParameters(){
		return Arrays.asList(new Object[][]{
			{2,5,7},
			{9,11,20},
		});
	}
	
	
	@Test
	public void test_add_numbers_parameterised() {
		accountService = new AccountServiceImpl();
		assertEquals(output, accountService.addNumbers(input1, input2));
	}
}
