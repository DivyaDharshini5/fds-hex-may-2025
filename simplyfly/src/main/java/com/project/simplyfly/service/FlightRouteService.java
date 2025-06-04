package com.project.simplyfly.service;

import org.springframework.stereotype.Service;

import com.project.simplyfly.exception.ResourceNotFoundException;
import com.project.simplyfly.model.Flight;
import com.project.simplyfly.model.FlightRoute;
import com.project.simplyfly.model.Route;
import com.project.simplyfly.repository.FlightRepository;
import com.project.simplyfly.repository.FlightRouteRepository;
import com.project.simplyfly.repository.RouteRepository;

@Service
public class FlightRouteService {
private RouteRepository routeRepository;
private FlightRepository flightRepository;
private FlightRouteRepository flightRouteRepository;
	public FlightRouteService(RouteRepository routeRepository, FlightRepository flightRepository,
		FlightRouteRepository flightRouteRepository) {
	this.routeRepository = routeRepository;
	this.flightRepository = flightRepository;
	this.flightRouteRepository = flightRouteRepository;
}
	public FlightRoute FlightByRoute(int flightId, int routeId) {
		Flight flight = flightRepository.findById(flightId)
	            .orElseThrow(() -> new ResourceNotFoundException("Flight ID Invalid"));
		 Route route = routeRepository.findById(routeId)
		            .orElseThrow(() -> new ResourceNotFoundException("Route ID Invalid"));

		 return flightRouteRepository.getUsingJPQL(flightId, routeId)
		            .orElseThrow(() -> new ResourceNotFoundException("FlightRoute not found"));
	}
	public FlightRoute createFlightRoute(int flightId, int routeId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found"));

        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));

        FlightRoute flightRoute = new FlightRoute();
        flightRoute.setFlight(flight);
        flightRoute.setRoute(route);

        return flightRouteRepository.save(flightRoute);
    }

}
