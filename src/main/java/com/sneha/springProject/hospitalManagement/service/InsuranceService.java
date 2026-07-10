package com.sneha.springProject.hospitalManagement.service;

import org.springframework.stereotype.Service;

import com.sneha.springProject.hospitalManagement.entity.Insurance;
import com.sneha.springProject.hospitalManagement.entity.Patient;
import com.sneha.springProject.hospitalManagement.repository.InsuranceRepository;
import com.sneha.springProject.hospitalManagement.repository.PatientRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsurancetoPatient(Insurance insurance, Long patientId){

       Patient patient = patientRepository.findById(patientId).
                        orElseThrow(() -> new EntityNotFoundException("Patient not found with id: "+patientId));
       
       patient.setInsurance(insurance);
       insurance.setPatient(patient); // Bi-directional consistency maintainance

       return patient;
    } 
}
