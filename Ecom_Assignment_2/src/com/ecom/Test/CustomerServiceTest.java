package com.ecom.Test;



import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ecom.Service.CustomerService;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Customer;

public class CustomerServiceTest {

	CustomerService  customerservice;
	@BeforeEach
	public void init() {
		//customerservice instance is created each time 
		customerservice= new CustomerService();
	}
	@Test
	public void insert() throws InvalidInputException  {
		//case 1:check whether the list of values is inserted into customer
		//expected list and actual list should be the same
		Customer insertedcustomer= new Customer(1,"Rajesh","kochin","1234567899","rajesh@gmail.com");
		customerservice.insert(insertedcustomer);
		Assertions.assertEquals(1,insertedcustomer.getId());
		 Assertions.assertTrue(insertedcustomer.getName().equalsIgnoreCase("Rajesh"));
		    Assertions.assertTrue(insertedcustomer.getCity().equalsIgnoreCase("Kochin"));
		    Assertions.assertTrue(insertedcustomer.getMobile().equalsIgnoreCase("1234567899"));
		    Assertions.assertTrue(insertedcustomer.getEmail().equalsIgnoreCase("rajesh@gmail.com"));
		
		//case 2 null customer list
	Customer nullcustomer= null;
	RuntimeException e= assertThrows(RuntimeException.class,()->{
		customerservice.insert(nullcustomer);
	});Assertions.assertEquals("customer cannot be null".toLowerCase(),
			e.getMessage().toLowerCase());
			
	//case 3 should not throw exception
	Customer c1= new Customer(1,"Rajesh","kochin","1234567899","rajesh@gmail.com");
	 assertDoesNotThrow(
				 ()-> {
	customerservice.insert(c1);});
	 Customer c2= new Customer(-91,"Rajesh","kochin","1234567899","rajesh@gmail.com");
	 e = assertThrows(
				RuntimeException.class, ()-> {
			customerservice.insert(c2);
		});
	 //case 4 customer_id cannot be negative value
		assertEquals("InvalidIDException:Customer id cannot be negative".toLowerCase(),
				e.getMessage().toLowerCase());
	
}
}
