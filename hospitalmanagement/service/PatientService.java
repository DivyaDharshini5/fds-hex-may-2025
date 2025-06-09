package com.springboot.CodingChallenge1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.CodingChallenge1.dto.PatientWithMedicalHistory;
import com.springboot.CodingChallenge1.model.MedicalHistory;
import com.springboot.CodingChallenge1.model.Patient;
import com.springboot.CodingChallenge1.model.User;
import com.springboot.CodingChallenge1.repository.MedicalHistoryRepository;
import com.springboot.CodingChallenge1.repository.PatientRepository;

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

       
         patientRepository.save(patient);

        
        histories.forEach(history -> history.setPatient(savedPatient));

        //save in db
        medicalHistoryRepository.saveAll(histories);

        return  patientRepository.save(patient);;
    }

}
