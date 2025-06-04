package com.project.simplyfly.service;

import org.springframework.stereotype.Service;

import com.project.simplyfly.model.Passenger;
import com.project.simplyfly.repository.PassengerRepository;

@Service
public class PassengerService {
private PassengerRepository passengerRepository;
	public Passenger AddPassenger(Passenger passenger) {
	
		return passengerRepository.save(passenger);
	}
	public PassengerService(PassengerRepository passengerRepository) {
		super();
		this.passengerRepository = passengerRepository;
	}
}
