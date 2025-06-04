package com.project.simplyfly.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.project.simplyfly.enums.Status;
import com.project.simplyfly.exception.ResourceNotFoundException;
import com.project.simplyfly.model.Booking;
import com.project.simplyfly.model.Coupon;
import com.project.simplyfly.model.Customer;
import com.project.simplyfly.model.Flight;
import com.project.simplyfly.repository.BookingRepository;
import com.project.simplyfly.repository.CouponRepository;
import com.project.simplyfly.repository.CustomerRepository;
import com.project.simplyfly.repository.FlightRepository;
@Service
public class BookingService {
private CustomerRepository customerRepository;
private BookingRepository bookingRepository;
private FlightRepository flightRepsoitory;
private CouponRepository couponRepository;

	public BookingService(CustomerRepository customerRepository, BookingRepository bookingRepository,
		FlightRepository flightRepsoitory, CouponRepository couponRepository) {
	super();
	this.customerRepository = customerRepository;
	this.bookingRepository = bookingRepository;
	this.flightRepsoitory = flightRepsoitory;
	this.couponRepository = couponRepository;
}

	public Booking AddBooking(int customerId,int flightId,int couponId,Booking booking) {
		//add customer
		Customer customer=customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer ID Invalid"));
		//add flight
		Flight flight = flightRepsoitory.findById(flightId)
	            .orElseThrow(() -> new ResourceNotFoundException("Flight ID Invalid"));
		//add coupon
		Coupon coupon = couponRepository.findById(couponId)
				.orElseThrow(() -> new ResourceNotFoundException("Coupon ID Invalid"));		
		   booking.setCustomer(customer);
		   booking.setFlight(flight);
		   booking.setCoupon(coupon);
		   double discount=coupon.getCouponName().getValue();
		   booking.setTotal_price(flight.getPrice_per_person()-(flight.getPrice_per_person()*(discount/100)));
		   bookingRepository.save(booking);
//		return bookingRepository.getUsingJPQL(customerId,flightId,couponId)
//				.orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
		//save booking
		   return booking;
		
	}

	public List<Booking> GetAll() {
		return bookingRepository.findByStatus(Status.CONFIRMED);
	}

	
	
	public Booking GetByCustomerID(int customerId) {
		
		return bookingRepository.GetByCustomerID(customerId);
	}

	
	

}

	
	


