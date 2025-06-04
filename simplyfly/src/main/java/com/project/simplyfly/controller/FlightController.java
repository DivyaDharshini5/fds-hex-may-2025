package com.project.simplyfly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.simplyfly.model.Flight;
import com.project.simplyfly.model.Route;
import com.project.simplyfly.service.FlightService;

@RestController
public class FlightController {
@Autowired
private FlightService flightService;

@PostMapping("/api/flight/add")
public Flight insertFlight(@RequestBody Flight flight){
	return flightService.insertFlight(flight);	
}
@GetMapping("/api/flight/get-all")
public List<Flight> getAllFlight(){
	return flightService.getAllFlight();
}
//@GetMapping("/api/flight/get-route/{routeId}")
//public Route getByRouteId(@RequestParam  int id){
//	return flightService.getByRouteId(id);
//	
//}
}
