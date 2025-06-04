package com.project.simplyfly.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.simplyfly.model.Flight;
import com.project.simplyfly.model.Seat;
import com.project.simplyfly.repository.FlightRepository;
import com.project.simplyfly.repository.RouteRepository;

@Service
public class FlightService {
     
	private FlightRepository flightRepository;
 private SeatService seatService;
	

	public FlightService(FlightRepository flightRepository, SeatService seatService) {
	super();
	this.flightRepository = flightRepository;
	this.seatService = seatService;
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

//	public Route getByRouteId(int id) {
//		flightRepository.
//				findById(id).orElseThrow(()->new RuntimeException("Route ID is Invalid"));
//		return routerepository.getById(id);
//	}

}
