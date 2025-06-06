package com.project.simplyfly.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.simplyfly.model.Owner;
import com.project.simplyfly.service.OwnerService;

@RestController
public class OwnerController {
	@Autowired
	private OwnerService ownerService;
@PostMapping("/api/owner/add")
public Owner insertOwner(@RequestBody Owner owner) {
	return ownerService.insertOwner(owner);	
}

@GetMapping("/api/owner/get-one")
public Owner getOwnerById(Principal principal) {
	// Ask spring username of loggedIn user using Principal interface 
	String username = principal.getName(); 
	//System.out.println("Authenticated user: " + principal.getName());

	return ownerService.getOwnerByUsername(username) ;
}	


}
