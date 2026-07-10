package com.sneha.springProject.hospitalManagement.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 500)
    private String reason;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    //nullable is false because an appointment cannot exist without a patient, so we need to make sure that the patient_id is always present in the appointment table.
    @ToString.Exclude
    private Patient patient; //owning side of relationship as appointement cannot exist without patient.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    private Doctor doctor; //owning side of relationship as appointement cannot exist without doctor.
}
