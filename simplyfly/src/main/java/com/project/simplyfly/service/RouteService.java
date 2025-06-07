package com.project.simplyfly.service;

import org.springframework.stereotype.Service;

import com.project.simplyfly.exception.ResourceNotFoundException;
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
	public Route updateRoute(Route updatedRoute, int routeId ) {
	    // Fetch existing route from DB
	    Route existingRoute = routerepository.findById(routeId)
	        .orElseThrow(() -> new ResourceNotFoundException("Route not found with id: " + routeId));

	    // Update fields only if they are provided
	    if (updatedRoute.getOrigin() != null) {
	        existingRoute.setOrigin(updatedRoute.getOrigin());
	    }
	    if (updatedRoute.getDestination() != null) {
	        existingRoute.setDestination(updatedRoute.getDestination());
	    }
	    if (updatedRoute.getDistance() != null) {
	        existingRoute.setDistance(updatedRoute.getDistance());
	    }
	    if (updatedRoute.getDuration() != null) {
	        existingRoute.setDuration(updatedRoute.getDuration());
	    }

	    // Save and return the updated route
	    return routerepository.save(existingRoute);
	}


}
