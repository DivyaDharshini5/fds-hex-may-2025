package com.project.simplyfly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.project.simplyfly.model.Supervisor;
import com.project.simplyfly.service.SupervisorService;

@RestController
public class SupervisorController {
	@Autowired
	private SupervisorService supervisorService;
	


@PostMapping("/api/supervisor/add")
public Supervisor insertSupervisor(@RequestBody Supervisor supervisor) {
	return supervisorService.insertSupervisor(supervisor);	
}
}