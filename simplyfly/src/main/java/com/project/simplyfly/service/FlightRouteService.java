package com.project.simplyfly.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.simplyfly.exception.ResourceNotFoundException;
import com.project.simplyfly.exception.UserNotFoundException;
import com.project.simplyfly.model.Customer;
import com.project.simplyfly.model.Flight;
import com.project.simplyfly.model.FlightRoute;
import com.project.simplyfly.model.Route;
import com.project.simplyfly.repository.CustomerRepository;
import com.project.simplyfly.repository.FlightRepository;
import com.project.simplyfly.repository.FlightRouteRepository;
import com.project.simplyfly.repository.RouteRepository;

@Service
public class FlightRouteService {
private RouteRepository routeRepository;
private FlightRepository flightRepository;
private CustomerRepository customerRepository;
private FlightRouteRepository flightRouteRepository;
	public FlightRouteService(RouteRepository routeRepository, FlightRepository flightRepository,
		CustomerRepository customerRepository, FlightRouteRepository flightRouteRepository) {
	super();
	this.routeRepository = routeRepository;
	this.flightRepository = flightRepository;
	this.customerRepository = customerRepository;
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
	public List<FlightRoute> getFlightsForCustomer(Principal principal) {
		String username = principal.getName();

	    Customer customer = customerRepository.getCustomerByUsername(username);
	    if (customer == null) {
	        throw new UserNotFoundException("Customer not found");
	    }

	    return flightRouteRepository.findAllByCustomerId(customer.getId());
	}

}
