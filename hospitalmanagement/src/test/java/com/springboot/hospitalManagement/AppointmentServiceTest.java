package com.springboot.hospitalManagement;



import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.hospitalManagement.model.Patient;
import com.springboot.hospitalManagement.repository.AppointmentRepository;
import com.springboot.hospitalManagement.service.AppointmentService;


@SpringBootTest
public class AppointmentServiceTest {

	  @InjectMocks
    private AppointmentService appointmentService;

    @Mock
    private AppointmentRepository appointmentRepository;

    private Patient patient1;
    

    @BeforeEach
    public void setUp() {
        patient1 = new Patient();
        patient1.setId(1);
        patient1.setName("John Doe");
        patient1.setAge(35);

      
    }

    @Test
    public void testGetPatientsByDoctor_Success() {
        
        when(appointmentRepository.getPatientsByDoctor("johndoe"))
                .thenReturn(Arrays.asList(patient1));

     
        List<Patient> result = appointmentService.getPatientsByDoctor("johndoe");

        // Assert
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("John Doe", result.get(0).getName());
//        Assertions.assertEquals("Jane Smith", result.get(1).getName());
    }


    
    
    
}