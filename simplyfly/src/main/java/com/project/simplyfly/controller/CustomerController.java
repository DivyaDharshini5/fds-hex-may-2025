package com.project.simplyfly.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.simplyfly.model.Customer;
import com.project.simplyfly.service.CustomerService;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {

@Autowired

private CustomerService customerService;
@PostMapping("/add")
public Customer insertCustomer(@RequestBody Customer customer) {
	return customerService.insertCustomer(customer);
}

@GetMapping("/get-all")
public List<Customer> getAllCustomer() {
	return customerService.getAllCustomer();
}
/*get logged in customer from principal
 * *
 * */
@GetMapping("/get-one")
public Customer getCustomerById(Principal principal) {
	// Ask spring username of loggedIn user using Principal interface 
	String username = principal.getName(); 
	//System.out.println("Authenticated user: " + principal.getName());

	return customerService.getCustomerByUsername(username) ;
}

@PutMapping("/update")
public Customer UpdateCustomer(@RequestBody Customer updatedcustomer,Principal principal) {
	 return customerService.UpdateCustomer(updatedcustomer,principal);
}
@DeleteMapping("/delete")
public void DeleteCustomer(Principal principal) {
	customerService.DeleteCustomer(principal);
	ResponseEntity.status(HttpStatus.OK).body("Customer Deleted");
}
}
