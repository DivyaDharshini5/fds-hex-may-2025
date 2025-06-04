package com.project.simplyfly.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.project.simplyfly.model.Customer;
import com.project.simplyfly.model.User;
import com.project.simplyfly.repository.CustomerRepository;


@Service
public class CustomerService {
private CustomerRepository customerRepository;
private UserService userService;

	public CustomerService(CustomerRepository customerRepository, UserService userService) {
	super();
	this.customerRepository = customerRepository;
	this.userService = userService;
}
	public Customer insertCustomer(Customer customer) {
		//Take user out of thie learner object
		User user = customer.getUser();
		//Give role to this user
		user.setRole("CUSTOMER");
		// save this user in the db
		user=userService.signUp(user);
		//Attach this user back to learner
		customer.setUser(user);
		
		return customerRepository.save(customer);
	}
	public List<Customer> getAllCustomer() {
		
		return customerRepository.findAll();//findall the customer and return as a list
	}
	public Customer getById(int customerId) {
		//Get list of customer info by the given by ID
		 return customerRepository.findById(customerId).orElseThrow(()->new RuntimeException("Customer ID is Invalid"));
		
		
	}
	public Customer getCustomerByUsername(String username) {
		
		Customer customer = customerRepository.getCustomerByUsername(username);
	    //System.out.println("Fetched customer: " + customer);
	    return customer;

	}
	

	

}
