package com.project.simplyfly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.simplyfly.model.Seat;

public interface SeatRepository extends JpaRepository<Seat,Integer>{
	Seat findByFlightIdAndSeatNumber(int flightId, String seatNumber);

	List<Seat> findByFlightId(int flightId);

	List<Seat> findByPassengerId(int passengerId);
	List<Seat> findByFlightIdAndIsReservedFalse(int flightId);
}
