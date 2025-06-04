package com.project.simplyfly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.simplyfly.model.Booking;
import com.project.simplyfly.service.BookingService;

@RestController
public class BookingController {
	@Autowired
	private BookingService bookingService;
	
@PostMapping("/api/booking/add/{customerId}/{flightId}/{couponId}")
public Booking AddBooking(@PathVariable int customerId,@PathVariable int flightId,@PathVariable int couponId,@RequestBody Booking booking) {
	
	return bookingService.AddBooking(customerId,flightId,couponId,booking);	
}
@GetMapping("/api/booking/get-all")
public List<Booking> GetAll(){
	return bookingService.GetAll();
}
@GetMapping("/api/booking/customer")
public Booking GetByCustomerID(@RequestParam int customerId) {
	return bookingService.GetByCustomerID(customerId);
}
}

