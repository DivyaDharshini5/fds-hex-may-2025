package com.project.simplyfly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.simplyfly.model.Seat;

public interface SeatRepository extends JpaRepository<Seat,Integer>{
	Seat findByFlightIdAndSeatNumber(int flightId, String seatNumber);

}
