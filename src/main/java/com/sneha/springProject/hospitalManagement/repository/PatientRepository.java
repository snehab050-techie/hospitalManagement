package com.sneha.springProject.hospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sneha.springProject.hospitalManagement.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}