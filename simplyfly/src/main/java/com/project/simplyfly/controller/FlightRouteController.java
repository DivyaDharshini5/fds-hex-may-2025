package com.project.simplyfly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.simplyfly.model.FlightRoute;
import com.project.simplyfly.service.FlightRouteService;
@RestController
public class FlightRouteController {
	@Autowired
private FlightRouteService flightRouteService;
	/*aim : search flight by route
	 * path:/api/flight/search/route/{flightId}/{routeId}
	 * method:post
	 * param:path  variabl{flightId,routeId},request Body{flightRooure}
	 * 
	 * response:flightRoute
	 * *
	 * */
@GetMapping("/api/flight/search/route/{flightId}/{routeId}")
public FlightRoute FlightByRoute(@PathVariable int flightId,
		@PathVariable int routeId) {
	return flightRouteService.FlightByRoute(flightId,routeId);
}
@PostMapping("/api/flightroute/add/{flightId}/{routeId}")
public FlightRoute addFlightRoute(@PathVariable int flightId, @PathVariable int routeId) {
    return flightRouteService.createFlightRoute(flightId, routeId);
}
}
