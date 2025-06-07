package com.project.simplyfly.service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.simplyfly.dto.BookingPaymentDto;
import com.project.simplyfly.enums.Status;
import com.project.simplyfly.exception.ResourceNotFoundException;
import com.project.simplyfly.model.Booking;
import com.project.simplyfly.model.Coupon;
import com.project.simplyfly.model.Customer;
import com.project.simplyfly.model.Flight;
import com.project.simplyfly.model.Passenger;
import com.project.simplyfly.model.Payment;
import com.project.simplyfly.model.Seat;
import com.project.simplyfly.repository.BookingRepository;
import com.project.simplyfly.repository.CouponRepository;
import com.project.simplyfly.repository.CustomerRepository;
import com.project.simplyfly.repository.PassengerRepository;
import com.project.simplyfly.repository.PaymentRepository;
import com.project.simplyfly.repository.SeatRepository;

@Service
public class BookingService {
	private CustomerRepository customerRepository;
	private PassengerRepository passengerRepository;
	private BookingRepository bookingRepository;
	private CouponRepository couponRepository;
	private PaymentRepository paymentRepository;
	private SeatRepository seatRepository;

	public BookingService(CustomerRepository customerRepository, PassengerRepository passengerRepository,
			BookingRepository bookingRepository, CouponRepository couponRepository, PaymentRepository paymentRepository,
			SeatRepository seatRepository) {
		this.customerRepository = customerRepository;
		this.passengerRepository = passengerRepository;
		this.bookingRepository = bookingRepository;
		this.couponRepository = couponRepository;
		this.paymentRepository = paymentRepository;
		this.seatRepository = seatRepository;
	}

	public List<Booking> GetAll() {
		return bookingRepository.findByStatus(Status.CONFIRMED);
	}

	public Booking GetByCustomerID(int customerId) {

		return bookingRepository.GetByCustomerID(customerId);
	}

	/***
	 * 1.get the logged in user with principal 2.find seat-selected passenger for
	 * the particular customer 3.from passenger get the flight 4.Apply coupon value
	 * as discount save booking and payment in db
	 **/
	public List<Booking> MakeBooking(BookingPaymentDto bookingPaymentDto, Principal principal) {
		String username = principal.getName();
		Customer customer = customerRepository.getCustomerByUsername(username);
		Coupon coupon;
		if (customer == null) {
			throw new ResourceNotFoundException("Customer not Found");
		}
		// Validate coupon

		if (bookingPaymentDto.getCouponType() != null) {
			coupon = couponRepository.findByCouponName(bookingPaymentDto.getCouponType());
		} else {
			throw new ResourceNotFoundException("Coupon not found");
		}
		double discount = bookingPaymentDto.getCouponType().getValue();

//     booking.setTotal_price(flight.getPrice_per_person()-(flight.getPrice_per_person()*(discount/100)));

		// for each passenger make booking
		List<Booking> BookingList = new ArrayList<>();
		for (Integer passengerId : bookingPaymentDto.getPassengerIds()) {
			Passenger passenger = passengerRepository.findById(passengerId)
					.orElseThrow(() -> new ResourceNotFoundException("Passenger not found for ID: "));
			if (passenger.getCustomer() == null || passenger.getCustomer().getId() != customer.getId()) {
				throw new ResourceNotFoundException("Passenger does not belong to this customer ");
			}
			// validate if passenger has a assigned seat
			Seat seat = seatRepository.findByPassengerId(passenger.getId()).orElseThrow(() -> new ResourceNotFoundException("Passenger does not have an assigned seat"));
			// get flight through seat
			Flight flight = seat.getFlight();
			if (flight == null)
				throw new ResourceNotFoundException("No flight found for passenger ");
			// calculating price with dicount from coupom
			double flightprice = flight.getPrice_per_person();
			double pricepaid = flightprice - (flightprice * (discount / 100));
			Booking booking = new Booking();
			
			booking.setCustomer(customer);
			booking.setTotal_price(pricepaid);
			booking.setCoupon(coupon);
			booking.setPassenger(passenger);
			booking.setBooking_date(LocalDate.now());
			booking.setStatus(Status.PROCESSING);
			booking.setFlight(flight);
			bookingRepository.save(booking);

			Payment payment = new Payment();
			payment.setAmount_paid(pricepaid);
			payment.setBooking(bookingRepository.save(booking));
			payment.setPayment_date(LocalDate.now());
			payment.setPaymentType(bookingPaymentDto.getPaymentType());
			paymentRepository.save(payment);
			BookingList.add(booking);
		}
		return BookingList;
	}

	public List<Booking> getByCustomer(Principal principal) {
		String username = principal.getName();
		Customer customer = customerRepository.getCustomerByUsername(username);
		// for every booking get the customer Id and see if it matches the loggedIn user
		// If matches filter the records and return them
		return bookingRepository.findAll().stream().filter(b -> b.getCustomer().getId() == customer.getId()).toList();
	}

}
