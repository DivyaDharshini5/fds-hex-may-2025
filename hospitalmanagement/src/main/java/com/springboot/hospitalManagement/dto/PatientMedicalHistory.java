package com.springboot.hospitalManagement.dto;

import java.util.List;

import com.springboot.hospitalManagement.model.MedicalHistory;
import com.springboot.hospitalManagement.model.Patient;

public class PatientMedicalHistory {
	
	private Patient patient;
	private List<MedicalHistory> medicalHistories;
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public List<MedicalHistory> getMedicalHistories() {
		return medicalHistories;
	}
	public void setMedicalHistories(List<MedicalHistory> medicalHistories) {
		this.medicalHistories = medicalHistories;
	}
	
	

}
