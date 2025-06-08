package com.project.simplyfly.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.simplyfly.dto.BookingDto;
import com.project.simplyfly.dto.BookingPaymentDto;
import com.project.simplyfly.model.Booking;
import com.project.simplyfly.service.BookingService;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
	@Autowired
	private BookingService bookingService;

	
@PostMapping("/makeBooking")
public ResponseEntity<List<BookingDto>> MakeBooking(@RequestBody BookingPaymentDto bookingPaymentDto,Principal principal) {
	  List<BookingDto> bookings = bookingService.makeBooking(bookingPaymentDto, principal);
	  return ResponseEntity.ok(bookings);
}
@GetMapping("/get-all")
public List<BookingDto> GetAll(){
	return bookingService.GetAll();
}
@GetMapping("/customer")
public List<Booking> GetByCustomer(Principal principal) {
	return bookingService.getByCustomer(principal);
}
@GetMapping("/owner")
public ResponseEntity<List<BookingDto>> getBookingsForOwner(Principal principal) {
    List<BookingDto> bookings = bookingService.getBookingsForOwner(principal);
    return ResponseEntity.ok(bookings);
}

}

