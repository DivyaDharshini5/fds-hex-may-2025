package com.springboot.hospitalManagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.hospitalManagement.dto.MedicalHistoryDto;
import com.springboot.hospitalManagement.exception.PatientIDNotFoundException;

import com.springboot.hospitalManagement.model.MedicalHistory;
import com.springboot.hospitalManagement.model.Patient;
import com.springboot.hospitalManagement.repository.MedicalHistoryRepository;
import com.springboot.hospitalManagement.repository.PatientRepository;

import jakarta.transaction.Transactional;

@Service
public class MedicalHistoryService {

    private PatientRepository patientRepository;
    private MedicalHistoryRepository medicalHistoryRepository;
    
    

    public MedicalHistoryService(PatientRepository patientRepository,
			MedicalHistoryRepository medicalHistoryRepository) {
		super();
		this.patientRepository = patientRepository;
		this.medicalHistoryRepository = medicalHistoryRepository;
	}



	@Transactional
    public void addBatchMedicalHistory(int patientId, List<MedicalHistory> historyList) {
		
		// fetch the patient by the patient id
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientIDNotFoundException("Patient Not Found"));

        // using batching to add the history in List of Medical History
        historyList.parallelStream().forEach(history -> history.setPatient(patient));

        
        // Save in DB
        medicalHistoryRepository.saveAll(historyList);
    }



	public List<MedicalHistoryDto> getPatientWithHistory(int patientId) {
		Patient patient = patientRepository.findById(patientId)
		        .orElseThrow(() -> new PatientIDNotFoundException("Patient not found"));
		
		List<MedicalHistory> medicalHistories = medicalHistoryRepository.findByPatientId(patientId);
		
		List<MedicalHistoryDto> dtoList = medicalHistories.stream().map(mh -> {
	        MedicalHistoryDto dto = new MedicalHistoryDto();
	        dto.setIllness(mh.getIllness());
	        dto.setNumberOfYears(mh.getNumOfYears());
	        dto.setMedication(mh.getCurrentMedication());
	        return dto;
	    }).collect(Collectors.toList());

	    return dtoList;
	}
}

