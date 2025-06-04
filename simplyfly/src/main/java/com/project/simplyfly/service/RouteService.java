package com.project.simplyfly.service;

import org.springframework.stereotype.Service;

import com.project.simplyfly.model.Route;
import com.project.simplyfly.repository.RouteRepository;

@Service
public class RouteService {
private RouteRepository routerepository;
	public RouteService(RouteRepository routerepository) {
	super();
	this.routerepository = routerepository;
}
	public Route insertRoute(Route route) {
		
		return routerepository.save(route);
	}

}
