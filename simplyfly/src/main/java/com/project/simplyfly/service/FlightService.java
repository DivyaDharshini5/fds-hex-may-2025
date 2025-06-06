package com.project.simplyfly.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.simplyfly.model.Flight;
import com.project.simplyfly.model.Seat;
import com.project.simplyfly.repository.FlightRepository;
import com.project.simplyfly.repository.SeatRepository;

@Service
public class FlightService {
     
	private FlightRepository flightRepository;
 private SeatService seatService;
 private SeatRepository seatRepository;
	public FlightService(FlightRepository flightRepository, SeatService seatService, SeatRepository seatRepository) {
	super();
	this.flightRepository = flightRepository;
	this.seatService = seatService;
	this.seatRepository = seatRepository;
}

	public Flight insertFlight(Flight flight) {
		flight=flightRepository.save(flight);
		//generate seats for each flight flight
	    List<Seat> seats = seatService.generateSeats(flight);
	    seatService.saveseats(seats);
	    return flight;
	}

	public List<Flight> getAllFlight() {
		
		return flightRepository.findAll();
	}



	public Flight DeleteFlight(int flightId) {
		Flight flight=flightRepository.findById(flightId).orElseThrow(()->
		new RuntimeException("Invalid ID.Flight Not Found"));
		List<Seat> seats = seatRepository.findByFlightId(flightId);
		seatRepository.deleteAll(seats);
		flightRepository.delete(flight);
		return flight;
	}

//	public Route getByRouteId(int id) {
//		flightRepository.
//				findById(id).orElseThrow(()->new RuntimeException("Route ID is Invalid"));
//		return routerepository.getById(id);
//	}

}
