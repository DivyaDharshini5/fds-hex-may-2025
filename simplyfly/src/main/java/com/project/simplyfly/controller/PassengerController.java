package com.project.simplyfly.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.simplyfly.dto.PassengerDto;
import com.project.simplyfly.model.Passenger;
import com.project.simplyfly.service.PassengerService;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {
	@Autowired
	private PassengerService passengerService;
	
@PostMapping("/add")
public Passenger AddPassenger(@RequestBody PassengerDto passengerdto,Principal principal) {
	
	return passengerService.AddPassenger(passengerdto,principal);
}
@PutMapping("/update")
public Passenger UpdatePassenger(@RequestBody Passenger updatedpassenger,Principal principal) {
	 return passengerService.UpdatePassenger(updatedpassenger,principal);
}
@DeleteMapping("/delete")
public void DeletePassenger(Principal principal) {
	passengerService.DeletePassenger(principal);
	ResponseEntity.status(HttpStatus.OK).body("PassengerDeleted");
}
}
