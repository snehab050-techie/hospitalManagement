package com.sneha.springProject.hospitalManagement.service;

import org.springframework.stereotype.Service;

import com.sneha.springProject.hospitalManagement.entity.Appointment;
import com.sneha.springProject.hospitalManagement.entity.Doctor;
import com.sneha.springProject.hospitalManagement.entity.Patient;
import com.sneha.springProject.hospitalManagement.repository.AppointmentRepository;
import com.sneha.springProject.hospitalManagement.repository.DoctorRepository;
import com.sneha.springProject.hospitalManagement.repository.PatientRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createNewAppointments(Appointment appointment, Long doctorId, Long patientId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: "+doctorId));
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient not found with id: "+patientId));

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment should not be created");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment); // to maintain bi-directional consistency

        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor); // this will automatically call the update, because it is dirty now

        doctor.getAppointments().add(appointment); // just for bi-redirectional consistency

        return appointment;
    }
    
}
