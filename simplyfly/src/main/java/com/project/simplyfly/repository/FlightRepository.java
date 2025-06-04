package com.project.simplyfly.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.project.simplyfly.model.Flight;

public interface FlightRepository extends JpaRepository<Flight,Integer>{

	
}
