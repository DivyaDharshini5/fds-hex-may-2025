package com.project.simplyfly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.simplyfly.model.Route;
import com.project.simplyfly.service.RouteService;
@RestController
public class RouteController {
	@Autowired
	private RouteService routeService;
@PostMapping("/api/route/add")
public Route insertRoute(@RequestBody Route route) {
	return routeService.insertRoute(route);
}
@PutMapping("/api/route/edit/{routeId}")
public Route updateRoute(@RequestBody Route updatedRoute, @PathVariable int routeId) {
    return routeService.updateRoute(updatedRoute, routeId);
}

}
