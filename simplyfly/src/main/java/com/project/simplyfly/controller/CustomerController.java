package com.project.simplyfly.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.simplyfly.model.Customer;
import com.project.simplyfly.service.CustomerService;


@RestController
public class CustomerController {

@Autowired
private CustomerService customerService;
@PostMapping("/api/customer/add")
public Customer insertCustomer(@RequestBody Customer customer) {
	return customerService.insertCustomer(customer);
}

@GetMapping("/api/customer/get-all")
public List<Customer> getAllCustomer() {
	return customerService.getAllCustomer();
}
/*get logged in customer from principal
 * *
 * */
@GetMapping("/api/customer/get-one")
public Customer getCustomerById(Principal principal) {
	// Ask spring username of loggedIn user using Principal interface 
	String username = principal.getName(); 
	//System.out.println("Authenticated user: " + principal.getName());

	return customerService.getCustomerByUsername(username) ;
}
}
