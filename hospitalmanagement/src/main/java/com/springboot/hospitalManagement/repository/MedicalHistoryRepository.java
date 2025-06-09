package com.springboot.hospitalManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.hospitalManagement.model.MedicalHistory;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Integer> {

	List<MedicalHistory> findByPatientId(int patientId);

}
