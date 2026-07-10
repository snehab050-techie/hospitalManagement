package com.sneha.springProject.hospitalManagement;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sneha.springProject.hospitalManagement.entity.Appointment;
import com.sneha.springProject.hospitalManagement.entity.Insurance;
import com.sneha.springProject.hospitalManagement.entity.Patient;
import com.sneha.springProject.hospitalManagement.service.AppointmentService;
import com.sneha.springProject.hospitalManagement.service.InsuranceService;

@SpringBootTest
public class InsuranceTests {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;
    
    @Test
    public void testInsurance(){

        Insurance insurance = Insurance.builder()
                              .policyNumber("HDFC_1234")
                              .providerName("HDFC")
                              .validUntil(LocalDate.of(2030, 12,12))
                              .build();

        
        Patient patient = insuranceService.assignInsurancetoPatient(insurance, 1L);
        System.out.println(patient);

        Patient updatedPatient = insuranceService.disassociatePatientFromInsurance(patient.getId());
        System.out.println(updatedPatient);
    }

    @Test
    public void testCreateAppointMent(){
        Appointment appointment = Appointment.builder()
                                  .appointmentTime(LocalDateTime.of(2028,7,10, 12, 30, 45))
                                  .reason("Skin infection")
                                  .build();

        var newAppointment = appointmentService.createNewAppointments(appointment, 1L, 2L);
        System.out.println(newAppointment);

        var updatedAppointment = appointmentService.reAssignAppointmentToAnotherDoctor(newAppointment.getId(), 3L);
        System.out.println(updatedAppointment);
    }
}
