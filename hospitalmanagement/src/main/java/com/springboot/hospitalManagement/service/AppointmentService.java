package com.springboot.hospitalManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.hospitalManagement.exception.DoctorIDNotFoundException;
import com.springboot.hospitalManagement.exception.PatientIDNotFoundException;
import com.springboot.hospitalManagement.model.Appointment;
import com.springboot.hospitalManagement.model.Doctor;
import com.springboot.hospitalManagement.model.Patient;
import com.springboot.hospitalManagement.repository.AppointmentRepository;
import com.springboot.hospitalManagement.repository.DoctorRepository;
import com.springboot.hospitalManagement.repository.PatientRepository;

@Service
public class AppointmentService {
	
	private AppointmentRepository appointmentRepository;
	private PatientRepository patientRepository;
	private DoctorRepository doctorRepository;
	
	

	public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository,
			DoctorRepository doctorRepository) {
		super();
		this.appointmentRepository = appointmentRepository;
		this.patientRepository = patientRepository;
		this.doctorRepository = doctorRepository;
	}



	public Appointment addAppointment(int patientId, int doctorId, Appointment appointment) {
		// get patient by id
	    Patient patient = patientRepository.findById(patientId)
	        .orElseThrow(() -> new PatientIDNotFoundException("Patient ID invalid: " + patientId));

	    // get doctor by ID
	    Doctor doctor = doctorRepository.findById(doctorId)
	        .orElseThrow(() -> new DoctorIDNotFoundException("Doctor ID Invalid: " + doctorId));

	    //setting patient and doctor to make appointment
	    appointment.setPatient(patient);
	    appointment.setDoctor(doctor);

	   

	    // Save  in DB
	    return appointmentRepository.save(appointment);
	}



	public List<Patient> getPatientsByDoctor(String username) {
		List<Patient> patient = appointmentRepository.getPatientsByDoctor(username);
		if(patient == null) {
			throw new PatientIDNotFoundException("Patient Not found");
		}
		return patient;
	}

}
