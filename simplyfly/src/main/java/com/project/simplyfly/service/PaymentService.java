package com.project.simplyfly.service;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.simplyfly.enums.PaymentType;
import com.project.simplyfly.enums.Status;
import com.project.simplyfly.exception.ResourceNotFoundException;
import com.project.simplyfly.model.Booking;
import com.project.simplyfly.model.Payment;
import com.project.simplyfly.repository.BookingRepository;
import com.project.simplyfly.repository.PaymentRepository;
@Service
public class PaymentService {
private PaymentRepository paymentRepository;
private BookingRepository bookingRepository;
	public PaymentService(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
	super();
	this.paymentRepository = paymentRepository;
	this.bookingRepository = bookingRepository;
}
	public Payment MakePayment(int bookingId ,Payment payment) {
		if (!EnumSet.allOf(PaymentType.class).contains(payment.getPaymentType())) {
	        throw new IllegalArgumentException("Invalid Payment type!");
	    }
		Booking booking=bookingRepository.findById(bookingId).
				orElseThrow(() -> new ResourceNotFoundException("Booking ID Invalid"));		
		payment.setPayment_date(LocalDate.now());
		payment.setStatus(Status.CONFIRMED);
		payment.setBooking(booking);
		return paymentRepository.save(payment);
	}
	public List<Payment> findByCustomerId(int customerId) {
		
		return paymentRepository.findByCustomerId(customerId);
	}
	public List<Payment> GetAll() {
		
		return paymentRepository.findAll();
	}

}
