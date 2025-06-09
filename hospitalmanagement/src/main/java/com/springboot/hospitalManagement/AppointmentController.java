package com.springboot.hospitalManagement;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.hospitalManagement.model.Appointment;
import com.springboot.hospitalManagement.model.Patient;
import com.springboot.hospitalManagement.service.AppointmentService;

@RestController
@RequestMapping("api/appointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	/*
	 * Aim : to make appointment by the patient and doctor
	 * Path : api/appointment/add
	 * Method :Post
	 * Input : patient id , doctor id , Appointment
	 * Response : Appointment
	 * */
	
	@PostMapping("/add")
	public Appointment addAppointment(@RequestParam int patientId,
									@RequestParam int doctorId,
									@RequestBody Appointment appointment) {
		
		return appointmentService.addAppointment(patientId, doctorId , appointment);
		
	}
	
	/*
     * Aim : To get all patient by Doctor id
     * Path : api/patient/doctor
     * Method : Get
     * Response : List<Patient>
     * */
    
    @GetMapping("/doctor")
    public List<Patient> getPatientsByDoctor(Principal principal) {
        String username = principal.getName();
        
        return appointmentService.getPatientsByDoctor(username);
    }

}
