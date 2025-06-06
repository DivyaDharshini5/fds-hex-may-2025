package com.project.simplyfly.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.simplyfly.dto.SeatSelectionDto;
import com.project.simplyfly.model.Seat;
import com.project.simplyfly.service.SeatService;

@RestController
@RequestMapping("/api/seat")
public class SeatController {
	@Autowired
private	SeatService seatService;
	@PostMapping("/select")
	public List<Seat> selectSeats(@RequestBody SeatSelectionDto requestSeat,Principal principal) {
	    return seatService.selectSeatForFlight(requestSeat,principal);
	}
    @GetMapping("/available")
    public List<Seat> getAvailableSeats(@RequestParam int flightId) {
        return seatService.getAvailableSeatsByFlightId(flightId);
    }

}
