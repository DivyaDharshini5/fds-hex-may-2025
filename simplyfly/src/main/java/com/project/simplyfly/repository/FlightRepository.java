package com.project.simplyfly.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.project.simplyfly.model.Flight;

public interface FlightRepository extends JpaRepository<Flight,Integer>{
   @Query("select f from Flight f where f.owner.user.username=?1")
	List<Flight> findByOwnerUsername(String ownerUsername);
    
	
}
