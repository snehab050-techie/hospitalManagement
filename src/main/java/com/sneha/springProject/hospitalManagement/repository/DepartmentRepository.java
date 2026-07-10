package com.sneha.springProject.hospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sneha.springProject.hospitalManagement.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
}
