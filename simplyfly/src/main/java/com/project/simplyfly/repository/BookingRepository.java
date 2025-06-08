package com.project.simplyfly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.simplyfly.enums.Status;
import com.project.simplyfly.model.Booking;

public interface BookingRepository extends JpaRepository<Booking,Integer>{
	 @Query("SELECT b FROM Booking b WHERE b.status = ?1")
	    List<Booking> findByStatus(Status status);

	    @Query("SELECT b FROM Booking b WHERE b.customer.id = ?1")
	    Booking getByCustomerId(int customerId);

	    @Query("SELECT b FROM Booking b WHERE b.customer.id = ?1 AND b.status = ?2")
	    List<Booking> findByCustomerIdAndStatus(int customerId, Status status);

	    @Query("SELECT b FROM Booking b WHERE b.flight.id IN ?1 AND b.status = ?2")
	    List<Booking> findByFlightIdsAndStatus(List<Integer> flightIds, Status status);
	


}
