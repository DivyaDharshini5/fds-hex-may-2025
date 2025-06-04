package com.project.simplyfly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.simplyfly.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger,Integer>{

}
