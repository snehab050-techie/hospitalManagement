package com.sneha.springProject.hospitalManagement;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sneha.springProject.hospitalManagement.entity.BloodGroupType;
import com.sneha.springProject.hospitalManagement.entity.Patient;
import com.sneha.springProject.hospitalManagement.repository.PatientRepository;
import com.sneha.springProject.hospitalManagement.service.PatientService;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;
    
    @Test
    public void testPatientRepository(){
        List<Patient> patients =patientRepository.findAll();
        System.out.println(patients);
    }

    @Test
    public void getPatientByIdTest(){
    
     Patient p = patientService.getPatientById(1L);
     System.out.println(p);
       
    }

    @Test
    public void getPatientsByNameTest(){
        List<Patient> patients = patientRepository.findByName("suhas");
        for(Patient p: patients){
            System.out.println(p);
        }   
    }

    @Test
    public void getPatientsByEmailIdTest(){
        List<Patient> patients = patientRepository.findByEmail("sneha@gmail.com");
        for(Patient p: patients){
            System.out.println(p);
        }   
    }

    @Test
    public void getPatientsByBloodGroupTest(){
        List<Patient> patients = patientRepository.findByBloodGroup(BloodGroupType.A_POSITIVE);
        for(Patient p: patients){
            System.out.println(p);
        }
    }

    @Test
    public void findByBornAfterDateTest(){
        List<Patient> patients = patientRepository.findByBornAfterDate(LocalDate.of(1980, 7, 25));
        for(Patient p: patients){
            System.out.println(p);
        }
    }

    @Test
    public void countBloodGroupByGroupByTest(){
        List<Object[]> results = patientRepository.countBloodGroupByGroupBy();
        for(Object[] result: results){
            System.out.println("Blood Group: " + result[0] + ", Count: " + result[1]);
        }
    }

    @Test
    public void findAllPatientsTest(){
        List<Patient> patients = patientRepository.findAllPatients();
        for(Patient p: patients){
            System.out.println(p);
        }
    }

    @Test
    public void updateNameByIdTest(){
        int updatedCount = patientRepository.updateNameById("Emily", 4L);
        System.out.println("Number of records updated: " + updatedCount);
    }
}
