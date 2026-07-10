package com.sneha.springProject.hospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sneha.springProject.hospitalManagement.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    
}
