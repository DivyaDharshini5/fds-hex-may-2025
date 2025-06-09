package com.springboot.hospitalManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.hospitalManagement.dto.PatientMedicalHistory;
import com.springboot.hospitalManagement.model.MedicalHistory;
import com.springboot.hospitalManagement.model.Patient;
import com.springboot.hospitalManagement.model.User;
import com.springboot.hospitalManagement.repository.MedicalHistoryRepository;
import com.springboot.hospitalManagement.repository.PatientRepository;

@Service
public class PatientService {
	private  PatientRepository patientRepository;
    private  UserService userService;
    private MedicalHistoryRepository medicalHistoryRepository;

   

    public PatientService(PatientRepository patientRepository, UserService userService,
			MedicalHistoryRepository medicalHistoryRepository) {
		super();
		this.patientRepository = patientRepository;
		this.userService = userService;
		this.medicalHistoryRepository = medicalHistoryRepository;
	}



	public Patient addPatientWithMedicalHistory(PatientMedicalHistory dto) {
        Patient patient = dto.getPatient();
        List<MedicalHistory> histories = dto.getMedicalHistories();

        
        User user = patient.getUser();
        user.setRole("PATIENT");
        user = userService.signUp(user); 
        patient.setUser(user);

       
         Patient savedPatient =patientRepository.save(patient);

        
        histories.forEach(history -> history.setPatient(savedPatient));

        //save in db
        medicalHistoryRepository.saveAll(histories);

        return  patientRepository.save(patient);
    }

}
