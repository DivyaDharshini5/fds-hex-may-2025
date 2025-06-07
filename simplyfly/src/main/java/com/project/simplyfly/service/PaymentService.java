package com.project.simplyfly.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.simplyfly.enums.Status;
import com.project.simplyfly.exception.ResourceNotFoundException;
import com.project.simplyfly.exception.UserNotFoundException;
import com.project.simplyfly.model.Booking;
import com.project.simplyfly.model.Customer;
import com.project.simplyfly.model.Payment;
import com.project.simplyfly.repository.BookingRepository;
import com.project.simplyfly.repository.CustomerRepository;
import com.project.simplyfly.repository.PaymentRepository;
@Service
public class PaymentService {
private PaymentRepository paymentRepository;
private BookingRepository bookingRepository;



private CustomerRepository customerRepository;

public PaymentService(PaymentRepository paymentRepository, BookingRepository bookingRepository,CustomerRepository customerRepository) {
	super();
	this.paymentRepository = paymentRepository;
	this.bookingRepository = bookingRepository;

	this.customerRepository = customerRepository;
}

//Process payment for all PROCESSING bookings of the logged-in customer
@Transactional
	public List<Payment> processPayment(Principal principal, boolean confirm) {
	String username = principal.getName();
	System.out.println(">>> Principal name: `" + username + "`");
		 Customer customer = customerRepository.getCustomerByUsername(username);
		 
	        if (customer == null) {
	            throw new ResourceNotFoundException("Customer not found");
	        }
	        List<Booking> toProcess = bookingRepository.findByCustomerIdAndStatus(customer.getId(),Status.PROCESSING);
	        if(toProcess.isEmpty()) {
	        	throw new ResourceNotFoundException("Np Pending bookings to process");
	        }
	        if(!confirm) {
	        	throw new RuntimeException("Payment cancelled by customer");	
	        }
	        String transaction_id=UUID.randomUUID().toString();
	        List<Payment> setpayments = toProcess.stream().map(b -> {
	            Payment payment = paymentRepository.findByBookingId(b.getId()).get(0); 
	            payment.setStatus(Status.CONFIRMED);
	            payment.setTransaction_id(transaction_id);
	            b.setStatus(Status.CONFIRMED);
	            return payment;
	        }).toList();
	        bookingRepository.saveAll(toProcess);
	        return paymentRepository.saveAll(setpayments);
	}

	public List<Payment> getPayments(Principal principal) {
		String username = principal.getName();
		 Customer customer = customerRepository.getCustomerByUsername(username);
	        if (customer == null) {
	            throw new IllegalStateException("Customer not found");
	        }
	        return paymentRepository.findByCustomerId(customer.getId());
	}
@Transactional
	public void cancelBooking(int bookingId, Principal principal) {
	String username = principal.getName();
	 Customer customer = customerRepository.getCustomerByUsername(username);
    Booking booking = bookingRepository.findById(bookingId).orElseThrow(
        () -> new ResourceNotFoundException("Booking not found"));
    if((booking.getCustomer().getId())!=(customer.getId())){
    	throw new UserNotFoundException("User not authorized to cancel booking");}
    	booking.setStatus(Status.CANCELLED);
    	bookingRepository.save(booking);
    }
		
@Transactional
	public void refundPayment(int bookingId) {
	List<Payment> payments = paymentRepository.findByBookingId(bookingId);
    if (payments.isEmpty()) {
        throw new IllegalStateException("No payments to refund");
    }
    payments.forEach(p -> p.setStatus(Status.REFUNDED));
    paymentRepository.saveAll(payments);
}
		
	}


