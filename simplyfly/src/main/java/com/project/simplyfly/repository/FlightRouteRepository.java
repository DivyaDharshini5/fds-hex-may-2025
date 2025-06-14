package com.project.simplyfly.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.simplyfly.model.FlightRoute;


public interface FlightRouteRepository extends JpaRepository<FlightRoute,Integer>{
	
	@Query("select fr from FlightRoute fr where fr.flight.id = ?1 and fr.route.id = ?2")
    Optional<FlightRoute> getUsingJPQL(int flightId, int routeId);
	@Query("SELECT b.flight FROM Booking b WHERE b.customer.id =?1")
	List<FlightRoute> findAllByCustomerId(int id);
}
