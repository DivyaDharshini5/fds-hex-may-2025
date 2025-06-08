package com.project.simplyfly.service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.simplyfly.dto.BookingDto;
import com.project.simplyfly.dto.BookingPaymentDto;
import com.project.simplyfly.dto.CouponDto;
import com.project.simplyfly.dto.CustomerDto;
import com.project.simplyfly.dto.FlightDto;
import com.project.simplyfly.dto.PassengerDto;
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
import com.project.simplyfly.repository.FlightRepository;
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
	private FlightRepository flightRepository; 

	public BookingService(CustomerRepository customerRepository, PassengerRepository passengerRepository,
			BookingRepository bookingRepository, CouponRepository couponRepository, PaymentRepository paymentRepository,
			SeatRepository seatRepository, FlightRepository flightRepository) {
		super();
		this.customerRepository = customerRepository;
		this.passengerRepository = passengerRepository;
		this.bookingRepository = bookingRepository;
		this.couponRepository = couponRepository;
		this.paymentRepository = paymentRepository;
		this.seatRepository = seatRepository;
		this.flightRepository = flightRepository;
	}



	public List<BookingDto> GetAll() {
		List<Booking> confirmedBookings = bookingRepository.findByStatus(Status.CONFIRMED);
	    List<BookingDto> bookingDtos = new ArrayList<>();

	    for (Booking booking : confirmedBookings) {
	        bookingDtos.add(convertToBookingDto(booking));
	    }

	    return bookingDtos;
	}



	/***
	 * 1.get the logged in user with principal 2.find seat-selected passenger for
	 * the particular customer 3.from passenger get the flight 4.Apply coupon value
	 * as discount save booking and payment in db
	 **/
	public List<BookingDto> makeBooking(BookingPaymentDto bookingPaymentDto, Principal principal) {
		String username = principal.getName();
		Customer customer = customerRepository.getCustomerByUsername(username);
	
		if (customer == null) {
			throw new ResourceNotFoundException("Customer not Found");
		}
		// Validate coupon

		  Coupon coupon = couponRepository.findByCouponName(bookingPaymentDto.getCouponType());
		    if (coupon == null) {
		        throw new ResourceNotFoundException("Coupon not found");
		    }
		double discount = bookingPaymentDto.getCouponType().getValue();


		// for each passenger make booking
		List<BookingDto> BookingList = new ArrayList<>();
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
			// calculating price with discount from coupon
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
			BookingDto dto = convertToBookingDto(booking);
			BookingList.add(dto);
	
		}
		return BookingList;
	}
	public BookingDto convertToBookingDto(Booking booking) {
		BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        dto.setBooking_date(booking.getBooking_date());
        dto.setStatus(booking.getStatus());
        dto.setTotal_price(booking.getTotal_price());

        Customer customer = booking.getCustomer();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setContact(customer.getContact());
        dto.setCustomer(customerDto);

        Passenger passenger = booking.getPassenger();
        PassengerDto passengerDto = new PassengerDto();
        passengerDto.setId(passenger.getId());
        passengerDto.setFull_name(passenger.getFull_name());
        passengerDto.setAge(passenger.getAge());
        passengerDto.setGender(passenger.getGender());
        passengerDto.setPassport_no(passenger.getPassport_no());
        dto.setPassenger(passengerDto);

        Flight flight = booking.getFlight();
        FlightDto flightDto = new FlightDto();
        flightDto.setId(flight.getId());
        flightDto.setNumber(flight.getNumber());
        flightDto.setOrigin(flight.getOrigin());
        flightDto.setDestination(flight.getDestination());
        flightDto.setDeparture_time(flight.getDeparture_time());
        flightDto.setArrival_time(flight.getArrival_time());
        flightDto.setPrice_per_person(flight.getPrice_per_person());
        flightDto.setSeatsAvailable(flight.getSeatsAvailable());
        dto.setFlight(flightDto);

        if (booking.getCoupon() != null) {
            CouponDto couponDto = new CouponDto();
            couponDto.setId(booking.getCoupon().getId());
            couponDto.setCouponName(booking.getCoupon().getCouponName());
            dto.setCoupon(couponDto);
        }

        return dto;
	}
	public List<BookingDto> getBookingsForOwner(Principal principal) {
	    String ownerUsername = principal.getName();
//flights for logged in owner
	    List<Flight> ownedFlights = flightRepository.findByOwnerUsername(ownerUsername);
	    if (ownedFlights.isEmpty()) {
	        throw new ResourceNotFoundException("No flights found for this owner.");
	    }

	    List<Integer> flightIds = new ArrayList<>();
	    ownedFlights.stream().forEach(flight -> flightIds.add(flight.getId()));

	    List<Booking> bookings = bookingRepository.findByFlightIdsAndStatus(flightIds, Status.CONFIRMED);

	    List<BookingDto> bookingDtos = new ArrayList<>();
	    bookings.stream().forEach(booking -> bookingDtos.add(convertToBookingDto(booking)));

	    return bookingDtos;
	}

	

	public List<Booking> getByCustomer(Principal principal) {
		String username = principal.getName();
		Customer customer = customerRepository.getCustomerByUsername(username);
		return bookingRepository.findAll().stream().filter(b -> b.getCustomer().getId() == customer.getId()).toList();
	}
	
}
