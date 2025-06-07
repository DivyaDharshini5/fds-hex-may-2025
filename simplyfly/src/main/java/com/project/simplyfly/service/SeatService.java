package com.project.simplyfly.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.simplyfly.dto.SeatSelectionDto;
import com.project.simplyfly.enums.SeatType;
import com.project.simplyfly.exception.ResourceNotFoundException;
import com.project.simplyfly.model.Customer;
import com.project.simplyfly.model.Flight;
import com.project.simplyfly.model.Passenger;
import com.project.simplyfly.model.Seat;
import com.project.simplyfly.repository.PassengerRepository;
import com.project.simplyfly.repository.SeatRepository;

@Service
public class SeatService {
   
	private SeatRepository seatRepository;
	private PassengerRepository passengerRepository;
	private CustomerService customerService;

public SeatService(SeatRepository seatRepository, PassengerRepository passengerRepository,
			CustomerService customerService) {
		this.seatRepository = seatRepository;
		this.passengerRepository = passengerRepository;
		this.customerService = customerService;
	}

public List<Seat> generateSeats(Flight flight) {
	//create a list of seats for every flight
	//it is called to generate every time a flight is created
	List<Seat>seats= new ArrayList<>();
	//generate for 6 columns
	String[] column= {"A","B","C","D","E","F"};
	int totalrows=10;//1A,1B,1c,1d,1e,1f.......30a,30b,30c,30d,30e,30f
	for(int i=1;i<=totalrows;i++) {
		for(String col:column) {//for each column
			 Seat seat = new Seat();
			seat.setFlight(flight);//generate seat for the flight  eveytime
			seat.setSeatNumber(i+col);
			seats.add(seat);
		}
	}
	return seats;
}

public void saveseats(List<Seat> seats) {
	 seatRepository.saveAll(seats);
}

public List<Seat> selectSeatForFlight(SeatSelectionDto requestSeat,Principal principal) {
	String username = principal.getName();
	Customer customer =customerService.getCustomerByUsername(username);
	  if (customer == null) {
	        throw new ResourceNotFoundException("Customer not found ");
	    }
    int flightId = requestSeat.getFlightId();//getFlightId from dto
    List<Integer> passengerIds = requestSeat.getPassengerIds();//getpassenger ids from dto
    List<String> seatNumbers=requestSeat.getSeatNumbers();
    List<SeatType> seatTypes = requestSeat.getSeatTypes();
    if(passengerIds.size()!=seatNumbers.size() || seatNumbers.size()!=seatTypes.size()) {
    	throw new RuntimeException("Each passenger should have their specific seatnumber and seattypes ");
    }
    List<Seat> selectedSeats=new ArrayList<>();
   
//for multiple seat selection
    for (int i = 0; i < passengerIds.size(); i++) {
        Integer passengerId = passengerIds.get(i);
        String seatNumber = seatNumbers.get(i);
        SeatType seatType = seatTypes.get(i);
        Passenger passenger = passengerRepository.findById(passengerId)
            .orElseThrow(() -> new RuntimeException("Passenger ID not found"));
        if (passenger.getCustomer().getId()!=customer.getId()) {
            throw new RuntimeException("Passenger ID  does not belong to the logged-in customer");
        }

        Seat seat = seatRepository.findByFlightIdAndSeatNumber(flightId, seatNumber);
        if (seat == null) {
            throw new RuntimeException("Seat " + seatNumber + " not found");
        }
        if (seat.getIsReserved()==true) {
            throw new RuntimeException("Seat " + seatNumber + " already taken");
        }

        //seatType validation 
        if (seatType == null || !EnumSet.allOf(SeatType.class).contains(seatType)) {
            throw new IllegalArgumentException("Invalid seat type for seat " + seatNumber);
        }

        seat.setSeatType(seatType);
        seat.setIsReserved(true);
        seat.setPassenger(passenger);
        seatRepository.save(seat);
        selectedSeats.add(seat);
        
        
    }

    return seatRepository.saveAll(selectedSeats);
}
public List<Seat> getAvailableSeatsByFlightId(int flightId) {
    return seatRepository.findByFlightIdAndIsReservedFalse(flightId);
}


}
