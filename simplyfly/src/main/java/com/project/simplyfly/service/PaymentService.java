package com.project.simplyfly.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.simplyfly.dto.PaymentDto;
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
private BookingService bookingService;
public PaymentService(PaymentRepository paymentRepository, BookingRepository bookingRepository,
		BookingService bookingService, CustomerRepository customerRepository) {
	this.paymentRepository = paymentRepository;
	this.bookingRepository = bookingRepository;
	this.bookingService = bookingService;
	this.customerRepository = customerRepository;
}

private CustomerRepository customerRepository;


//Process payment for all PROCESSING bookings of the logged-in customer
@Transactional
	public List<PaymentDto> processPayment(Principal principal, boolean confirm) {
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
	        List<PaymentDto> paymentDtos = new ArrayList<>();
	        paymentRepository.saveAll(setpayments).stream().forEach(p->paymentDtos.add(convertToPaymentDto(p)));
	        return paymentDtos;      
	}

	public List<PaymentDto> getPayments(Principal principal) {
		String username = principal.getName();
		 Customer customer = customerRepository.getCustomerByUsername(username);
	        if (customer == null) {
	            throw new IllegalStateException("Customer not found");
	        }
	        List<Payment> payments = paymentRepository.findByCustomerId(customer.getId());
	        List<PaymentDto> paymentDtos = new ArrayList<>();

	        for (Payment payment : payments) {
	            paymentDtos.add(convertToPaymentDto(payment));
	        }

	        return paymentDtos;
	}
	public PaymentDto convertToPaymentDto(Payment payment) {
	    PaymentDto dto = new PaymentDto();
	    dto.setId(payment.getId());
	    dto.setPayment_date(payment.getPayment_date());
	    dto.setAmount_paid(payment.getAmount_paid());
	    dto.setTransaction_id(payment.getTransaction_id());
	    dto.setStatus(payment.getStatus().toString());
	    dto.setPaymentType(payment.getPaymentType());

	    
	    dto.setBooking(bookingService.convertToBookingDto(payment.getBooking()));

	    return dto;
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


