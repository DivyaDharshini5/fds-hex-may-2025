package com.project.simplyfly.service;



import java.security.Principal;
import java.util.List;
import com.project.simplyfly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.simplyfly.exception.UserNotFoundException;
import com.project.simplyfly.model.Customer;
import com.project.simplyfly.model.User;
import com.project.simplyfly.repository.CustomerRepository;


@Service
public class CustomerService {

    private  UserRepository userRepository;
private CustomerRepository customerRepository;
private UserService userService;
@Autowired
private PasswordEncoder passwordEncoder;
	public CustomerService(CustomerRepository customerRepository, UserService userService, UserRepository userRepository) {
	super();
	this.userRepository = userRepository;
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
	//Only loggedin user can update their credentials
	public Customer UpdateCustomer(Customer updatedcustomer,Principal principal) {
		//get loggedin user 
		String username=principal.getName();
		//get existing user
		Customer customer=customerRepository.getCustomerByUsername(username);
		if(customer==null) {
			throw new UserNotFoundException("User Not Found");
		}
		//if user entered new name then set the name to existing user
		if(updatedcustomer.getName()!=null) {
			customer.setName(updatedcustomer.getName());
		}
		//if user entered new contact then set the contact to existing user
		if(updatedcustomer.getContact()!=null) {
			customer.setContact(updatedcustomer.getContact());
		}
		//if user entered new username and password then set the name and pass to existing user
		if(updatedcustomer.getUser()!=null) {
			customer.getUser().setUsername(updatedcustomer.getUser().getUsername());//existing user
			  if (updatedcustomer.getUser().getPassword() != null) {
				  //enocde the new password before updating
				  String encodedPassword = passwordEncoder.encode(updatedcustomer.getUser().getPassword());
		            customer.getUser().setPassword(encodedPassword);
		        }
		}
		return customerRepository.save(customer);
	}
	public void DeleteCustomer(Principal principal) {
		//get logged in user
		String username=principal.getName();
		Customer customer = customerRepository.getCustomerByUsername(username);
		customerRepository.delete(customer);
		//Get user associated with that customer
		User user =customer.getUser();
		//delete that user too
		userRepository.delete(user);
	}
	

	

}
