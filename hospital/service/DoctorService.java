package com.springboot.CodingChallenge1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.CodingChallenge1.enums.Speciality;
import com.springboot.CodingChallenge1.model.Doctor;
import com.springboot.CodingChallenge1.model.User;
import com.springboot.CodingChallenge1.repository.DoctorRepository;


@Service
public class DoctorService {

	
    private DoctorRepository doctorRepository;
    private UserService userService;
    
    

    public DoctorService(DoctorRepository doctorRepository, UserService userService) {
		super();
		this.doctorRepository = doctorRepository;
		this.userService = userService;
	}



	public Doctor addDoctor(Doctor doctor) {
    	
		User user = doctor.getUser();
		
		
		user.setRole("DOCTOR");
		
		
		user = userService.signUp(user);
		
		
		doctor.setUser(user);
    	
        return doctorRepository.save(doctor);
    }

}
