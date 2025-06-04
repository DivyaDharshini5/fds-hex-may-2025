package com.project.simplyfly.service;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.springframework.stereotype.Service;
import com.project.simplyfly.controller.RouteController;
import com.project.simplyfly.enums.CouponType;
import com.project.simplyfly.enums.SeatType;
import com.project.simplyfly.model.Flight;
import com.project.simplyfly.model.Passenger;
import com.project.simplyfly.model.Seat;
import com.project.simplyfly.repository.PassengerRepository;
import com.project.simplyfly.repository.SeatRepository;

@Service
public class SeatService {

    private final RouteController routeController;
	private SeatRepository seatRepository;
	private PassengerRepository passengerRepository;
public List<Seat> generateSeats(Flight flight) {
	//create a list of seats for every flight
	//it is called to generate every time a flight is created
	List<Seat>seats= new ArrayList<>();
	//generate for 6 columns
	String[] column= {"A","B","C","D","E","F"};
	int totalrows=30;//1A,1B,1c,1d,1e,1f.......30a,30b,30c,30d,30e,30f
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
public SeatService(SeatRepository seatRepository, PassengerRepository passengerRepository, RouteController routeController) {
	super();
	this.seatRepository = seatRepository;
	this.passengerRepository = passengerRepository;
	this.routeController = routeController;
}
public void saveseats(List<Seat> seats) {
	 seatRepository.saveAll(seats);
}

public Seat selectSeatForFlight(int flightId,String seatNumber, int passengerId,Seat requestSeat) {
	 Seat seat=seatRepository.findByFlightIdAndSeatNumber(flightId,seatNumber);
	//get the seat based on given flightId and seatnumber by user and set it to seat
	if(seat==null) {
		throw new RuntimeException("Seat Not Found");}
	else if (seat.getIs_reserved()==true) {
		throw new RuntimeException("Seat already taken");
	}

	//if the seatType entered by customer is not null
	//set seatType
	if (requestSeat.getSeatType() != null) {
	seat.setSeatType(requestSeat.getSeatType());
	}
	//Check if the seatType given by the customer is in the enum class of seatType
	if (!EnumSet.allOf(SeatType.class).contains(seat.getSeatType())) {
        throw new IllegalArgumentException("Invalid seat type!");
    }
	seat.setIs_reserved(true);//if not  reserved select seat
	Passenger passenger =passengerRepository.findById(passengerId).orElseThrow(()->
	new RuntimeException("Passenger ID not found")) ;
	seat.setPassenger(passenger);//set the passengerId for the selected seat
	return seatRepository.save(seat);//save seat in db
}
}
