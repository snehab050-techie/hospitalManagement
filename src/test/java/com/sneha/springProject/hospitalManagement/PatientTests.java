package com.sneha.springProject.hospitalManagement;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sneha.springProject.hospitalManagement.entity.Patient;
import com.sneha.springProject.hospitalManagement.repository.PatientRepository;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;
    
    @Test
    public void testPatientRepository(){
        List<Patient> patients =patientRepository.findAll();
        System.out.println(patients);
    }
}
