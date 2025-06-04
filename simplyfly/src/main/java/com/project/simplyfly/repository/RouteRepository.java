package com.project.simplyfly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.simplyfly.model.Route;

public interface RouteRepository extends JpaRepository<Route,Integer>{
	
}
