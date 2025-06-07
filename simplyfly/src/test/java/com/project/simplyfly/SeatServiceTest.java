package com.project.simplyfly;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.project.simplyfly.dto.SeatSelectionDto;
import com.project.simplyfly.enums.SeatType;
import com.project.simplyfly.model.Customer;
import com.project.simplyfly.model.Flight;
import com.project.simplyfly.model.Passenger;
import com.project.simplyfly.model.Seat;
import com.project.simplyfly.model.User;
import com.project.simplyfly.repository.FlightRepository;
import com.project.simplyfly.repository.PassengerRepository;
import com.project.simplyfly.repository.SeatRepository;
import com.project.simplyfly.service.CustomerService;
import com.project.simplyfly.service.SeatService;

@SpringBootTest
public class SeatServiceTest {
@InjectMocks
private SeatService seatService;
@Mock
private SeatRepository seatRepository;
@Mock
private PassengerRepository passengerRepository;
@Mock
private CustomerService customerService;
@Mock
private FlightRepository flightRepository;
private Principal principal;
private Customer customer;
private Passenger passenger;
private  Flight flight;
private Seat seat;
private User user;
@BeforeEach
public void init() {
	principal=()->"mockuser";
	customer=new Customer();
	customer.setId(1);
	 customer.setName("Mock Customer");
	    customer.setContact("1234567890");
	    user = new User();
	    user.setUsername("mockuser");
	    customer.setUser(user);
	passenger=new Passenger();
	passenger.setId(101);
	passenger.setCustomer(customer);
	
	flight=new Flight();
	flight.setId(1);
	flight.setSeatsAvailable(50);
	  seat = new Seat();
      seat.setSeatNumber("3C");
      seat.setFlight(flight);
      seat.setIsReserved(false);
	
}
@Test
public void selectSeatTest() {
	SeatSelectionDto dto = new SeatSelectionDto();
	dto.setFlightId(1);
	dto.setPassengerIds(List.of(101));
	dto.setSeatNumbers(List.of("3C"));
	dto.setSeatTypes(List.of(SeatType.MIDDLE));
	
when(customerService.getCustomerByUsername("mockuser")).thenReturn(customer);
when(passengerRepository.findById(101)).thenReturn(Optional.of(passenger));
when(seatRepository.findByFlightIdAndSeatNumber(1, "3C")).thenReturn(seat);
List<Seat> result = seatService.selectSeatForFlight(dto, principal);
assertEquals(1, result.size());
assertTrue(result.get(0).getIsReserved());
assertEquals(passenger, result.get(0).getPassenger());
assertEquals(SeatType.MIDDLE, result.get(0).getSeatType());
}
}
