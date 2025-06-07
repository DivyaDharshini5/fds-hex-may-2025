package com.project.simplyfly;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.security.Principal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.simplyfly.enums.Status;
import com.project.simplyfly.model.Booking;
import com.project.simplyfly.model.Customer;
import com.project.simplyfly.model.Payment;
import com.project.simplyfly.model.User;
import com.project.simplyfly.repository.BookingRepository;
import com.project.simplyfly.repository.CustomerRepository;
import com.project.simplyfly.repository.PaymentRepository;
import com.project.simplyfly.service.PaymentService;

@SpringBootTest
public class ProcessPaymentTest {
	@InjectMocks
	private PaymentService paymentService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private Principal principal;
    private Customer customer;
    private Booking booking;
    private Payment payment;
    @BeforeEach
    public void setup() {
        User user = new User();
        user.setUsername("mockuser");

        customer = new Customer();
        customer.setId(1);
        customer.setUser(user);

        booking = new Booking();
        booking.setId(10);
        booking.setStatus(Status.PROCESSING);
        booking.setCustomer(customer);

        payment = new Payment();
        payment.setStatus(Status.PROCESSING);
    }   
    @Test
    public void testProcessPayment() {
    	when(principal.getName()).thenReturn("mockuser");
    	 when(customerRepository.getCustomerByUsername("mockuser")).thenReturn(customer);
    	 when(bookingRepository.findByCustomerIdAndStatus(1, Status.PROCESSING))
         .thenReturn(List.of(booking));
    	 when(paymentRepository.findByBookingId(10)).thenReturn(List.of(payment));
         when(bookingRepository.saveAll(List.of(booking))).thenReturn(List.of(booking));
         when(paymentRepository.saveAll(List.of(payment))).thenReturn(List.of(payment));
		List<Payment> result =paymentService.processPayment(principal, true);
		assertEquals(Status.CONFIRMED, result.get(0).getStatus());
        assertEquals(Status.CONFIRMED, booking.getStatus());
        assertEquals(36, result.get(0).getTransaction_id().length());
    }
}
