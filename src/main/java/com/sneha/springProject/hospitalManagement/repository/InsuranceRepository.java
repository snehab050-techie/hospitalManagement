package com.sneha.springProject.hospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sneha.springProject.hospitalManagement.entity.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}
