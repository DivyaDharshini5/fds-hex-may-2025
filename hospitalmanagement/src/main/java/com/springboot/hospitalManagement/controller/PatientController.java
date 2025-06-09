package com.springboot.hospitalManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.hospitalManagement.dto.PatientMedicalHistory;
import com.springboot.hospitalManagement.model.Patient;
import com.springboot.hospitalManagement.service.PatientService;

@RestController
@RequestMapping("api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;
    
    /*
     * Aim : To insert new Patient
     * Path : api/patient/add
     * Method : POST
     * Input : new Patient
     * Response : Patient
     * */

    @PostMapping("/add")
    public ResponseEntity<?> addPatientMedicalHistory(@RequestBody PatientMedicalHistory dto) {
        Patient newPatient = patientService.addPatientMedicalHistory(dto);
        return ResponseEntity.ok(newPatient);
    }
    
    
}
