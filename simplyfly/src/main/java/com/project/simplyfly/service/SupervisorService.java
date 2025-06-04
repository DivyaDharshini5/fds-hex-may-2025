package com.project.simplyfly.service;

import org.springframework.stereotype.Service;
import com.project.simplyfly.model.Supervisor;
import com.project.simplyfly.model.User;
import com.project.simplyfly.repository.SupervisorRepository;

@Service
public class SupervisorService {
	private SupervisorRepository supervisorRepository;
	private UserService userService;
	public SupervisorService(SupervisorRepository supervisorRepository, UserService userService) {
		this.supervisorRepository = supervisorRepository;
		this.userService = userService;
	}
	public Supervisor insertSupervisor(Supervisor supervisor) {
		User user =supervisor.getUser();
		user.setRole("SUPERVISOR");
		user=userService.signUp(user);
		supervisor.setUser(user);
		return supervisorRepository.save(supervisor);
	}

}
