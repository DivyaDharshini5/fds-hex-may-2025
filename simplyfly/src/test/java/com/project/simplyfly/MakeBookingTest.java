package com.project.simplyfly;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.simplyfly.dto.BookingDto;
import com.project.simplyfly.dto.BookingPaymentDto;
import com.project.simplyfly.enums.CouponType;
import com.project.simplyfly.enums.PaymentType;
import com.project.simplyfly.enums.Status;
import com.project.simplyfly.model.Booking;
import com.project.simplyfly.model.Coupon;
import com.project.simplyfly.model.Customer;
import com.project.simplyfly.model.Flight;
import com.project.simplyfly.model.Passenger;
import com.project.simplyfly.model.Payment;
import com.project.simplyfly.model.Seat;
import com.project.simplyfly.model.User;
import com.project.simplyfly.repository.BookingRepository;
import com.project.simplyfly.repository.CouponRepository;
import com.project.simplyfly.repository.CustomerRepository;
import com.project.simplyfly.repository.PassengerRepository;
import com.project.simplyfly.repository.PaymentRepository;
import com.project.simplyfly.repository.SeatRepository;
import com.project.simplyfly.service.BookingService;
@SpringBootTest
public class MakeBookingTest {
	@InjectMocks
    private BookingService bookingService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CouponRepository couponRepository;
    @Mock
    private PassengerRepository passengerRepository;
    @Mock
    private SeatRepository seatRepository;
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private Principal principal;	
    
    private Customer customer;
    private Passenger passenger;
    private Seat seat;
    private Flight flight;
    private Coupon coupon;
    
    private User user;
    @BeforeEach
    public void init() {
    	
        customer = new Customer();
        customer.setId(1);
    
   	 customer.setName("Mock Customer");
   	    customer.setContact("1234567890");
   	    user = new User();
   	    user.setUsername("mockuser");
   	    customer.setUser(user);

        passenger = new Passenger();
        passenger.setId(101);
        passenger.setCustomer(customer);

        flight = new Flight();
        flight.setId(10);
        flight.setPrice_per_person(1000.0);

        seat = new Seat();
        seat.setPassenger(passenger);
        seat.setFlight(flight);

        coupon = new Coupon();
        coupon.setCouponName(CouponType.DIWALI);
       
    }
    @Test
    public void testMakeBooking() {
    	 BookingPaymentDto dto = new BookingPaymentDto();
         dto.setPassengerIds(List.of(101));	
         dto.setCouponType(CouponType.DIWALI);
         dto.setPaymentType(PaymentType.UPI);
         //when mock principal is called return mockuser
         when(principal.getName()).thenReturn("mockuser");
         
         when(customerRepository.getCustomerByUsername("mockuser")).thenReturn(customer);
         when(couponRepository.findByCouponName(CouponType.DIWALI)).thenReturn(coupon);
         when(passengerRepository.findById(101)).thenReturn(Optional.of(passenger));
         when(seatRepository.findByPassengerId(101)).thenReturn(Optional.of(seat));
         Booking savedBooking = new Booking();
         savedBooking.setId(1);
         when(bookingRepository.save(any(Booking.class))).thenReturn(savedBooking);
         when(paymentRepository.save(any(Payment.class))).thenReturn(new Payment());
         List<BookingDto> result = bookingService.makeBooking(dto, principal);
         assertEquals(1, result.size());
         BookingDto resultBooking = result.get(0);
         assertEquals(customer, resultBooking.getCustomer());
         assertEquals(passenger, resultBooking.getPassenger());
         assertEquals(flight, resultBooking.getFlight());
         assertEquals(Status.PROCESSING, resultBooking.getStatus());
         double expectedPrice = 1000 - (1000 * 0.10);
         assertEquals(expectedPrice, resultBooking.getTotal_price());
    }
}
