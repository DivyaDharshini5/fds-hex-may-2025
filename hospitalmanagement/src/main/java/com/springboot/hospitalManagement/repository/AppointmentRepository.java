package com.springboot.hospitalManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.hospitalManagement.model.Appointment;
import com.springboot.hospitalManagement.model.Patient;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

	@Query("select a.patient from Appointment a where a.doctor.user.username = ?1")
	List<Patient> getPatientsByDoctor(String username);

}
