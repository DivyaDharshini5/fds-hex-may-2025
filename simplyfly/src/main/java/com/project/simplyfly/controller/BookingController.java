package com.project.simplyfly.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.simplyfly.dto.BookingPaymentDto;
import com.project.simplyfly.model.Booking;
import com.project.simplyfly.service.BookingService;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
	@Autowired
	private BookingService bookingService;

	
@PostMapping("/makeBooking")
public List<Booking> MakeBooking(@RequestBody BookingPaymentDto bookingPaymentDto,Principal principal) {
	return bookingService.MakeBooking(bookingPaymentDto,principal);	
}
@GetMapping("/get-all")
public List<Booking> GetAll(){
	return bookingService.GetAll();
}
@GetMapping("/customer")
public List<Booking> GetByCustomer(Principal principal) {
	return bookingService.getByCustomer(principal);
}

}

