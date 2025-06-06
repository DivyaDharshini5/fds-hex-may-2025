package com.project.simplyfly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.simplyfly.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger,Integer>{
    @Query("select p from Passenger p where p.customer.user.username=?1")
	List<Passenger> getByUsername(String username);
    @Query("select p from Passenger p where p.customer.user.username=?1")
	Passenger getByPassengerUsername(String username);

}
