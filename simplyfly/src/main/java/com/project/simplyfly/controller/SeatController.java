package com.project.simplyfly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.simplyfly.model.Seat;
import com.project.simplyfly.service.SeatService;

@RestController

public class SeatController {
	@Autowired
private	SeatService seatService;
@PostMapping("/api/selectseat")
public Seat selectSeatForFlight(@RequestParam int flightId,@RequestParam String seatNumber,
		@RequestParam int passengerId,@RequestBody Seat requestSeat) { 
	return seatService.selectSeatForFlight(flightId,seatNumber,passengerId,requestSeat);
	
}
}
