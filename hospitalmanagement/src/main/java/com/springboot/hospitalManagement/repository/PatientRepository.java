package com.springboot.hospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.hospitalManagement.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>{

}
