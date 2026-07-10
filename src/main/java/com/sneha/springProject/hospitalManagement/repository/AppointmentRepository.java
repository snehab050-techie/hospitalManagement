package com.sneha.springProject.hospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sneha.springProject.hospitalManagement.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
