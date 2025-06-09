package com.springboot.hospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.hospitalManagement.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer>{

}
