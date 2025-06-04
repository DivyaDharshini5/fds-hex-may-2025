package com.project.simplyfly.service;


import org.springframework.stereotype.Service;

import com.project.simplyfly.model.Owner;
import com.project.simplyfly.model.User;
import com.project.simplyfly.repository.OwnerRepository;
@Service
public class OwnerService {
private OwnerRepository ownerRepository;
private UserService userService;
	public OwnerService(OwnerRepository ownerRepository, UserService userService) {
	super();
	this.ownerRepository = ownerRepository;
	this.userService = userService;
}
	public Owner insertOwner(Owner owner) {
		User user =owner.getUser();
		user.setRole("OWNER");
		user=userService.signUp(user);
		owner.setUser(user);
		return ownerRepository.save(owner);
	}
public Owner getOwnerByUsername(String username) {
		
		Owner owner = ownerRepository.getOwnerByUsername(username);
	    return owner;
}
}
