package com.project.simplyfly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.simplyfly.model.Flight;
import com.project.simplyfly.service.FlightService;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
@Autowired
private FlightService flightService;

@PostMapping("/add")
public Flight insertFlight(@RequestBody Flight flight){
	return flightService.insertFlight(flight);	
}
@GetMapping("/get-all")
public List<Flight> getAllFlight(){
	return flightService.getAllFlight();
}
//@GetMapping("/api/flight/get-route/{routeId}")
//public Route getByRouteId(@RequestParam  int id){
//	return flightService.getByRouteId(id);
//	
//}
@DeleteMapping("/delete/{flightId}")
public Flight DeleteFlight(@PathVariable int flightId) {

	return flightService.DeleteFlight(flightId);
}
}
