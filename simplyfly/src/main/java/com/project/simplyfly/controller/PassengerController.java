package com.project.simplyfly.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.simplyfly.model.Booking;
import com.project.simplyfly.model.Customer;
import com.project.simplyfly.model.Passenger;
import com.project.simplyfly.service.BookingService;
import com.project.simplyfly.service.CustomerService;
import com.project.simplyfly.service.PassengerService;

@RestController
public class PassengerController {
	@Autowired
	private PassengerService passengerService;
	@Autowired
	private CustomerService customerService;
	@Autowired 
	private BookingService bookingService;
@PostMapping("/api/add/passenger")
public Passenger AddPassenger(@RequestBody Passenger passenger,Principal principal) {
	//get username of loggedin customer
	String username=principal.getName();
	System.out.println(principal.getName());
	//fetch the customer based on username
	Customer customer=customerService.getCustomerByUsername(username);
	//attach customer to passenger
	passenger.setCustomer(customer);
	// attach booking
	Booking booking = bookingService.GetByCustomerID(customer.getId());
	passenger.setBooking(booking);
	//save the passenger with the customer linked
	return passengerService.AddPassenger(passenger);
}
}
