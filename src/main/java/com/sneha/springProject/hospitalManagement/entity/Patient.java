package com.sneha.springProject.hospitalManagement.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@Table(
    name = "patient",
    uniqueConstraints = {
        // @UniqueConstraint(name = "email_unique", columnNames = "email"),
        @UniqueConstraint(name = "name_unique", columnNames = "name"),
        @UniqueConstraint(name = "email_birthdate_unique", columnNames = {"email", "birthDate"})
    },
    indexes = {
        @Index(name = "idx_birthDate", columnList = "birthDate")
    }
)
public class Patient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 40)
    // now this will create a coulmn named "patient_name" in the DB but the older column name "name" 
    // will still be there in the DB, so we need to drop the old column "name" from the DB and then 
    // run the application again to create the new column "patient_name" in the DB
    // but this is not a good practice, as we may  face data inconsistency issues, there comes data migration 
    // tools like Flyway and Liquibase, which can be used to manage the database schema changes in a more controlled way.
    private String name;

    @ToString.Exclude
    private LocalDate birth_date;

    @Column(unique =true, nullable = false)
    private String email;

    private String gender;

    @CreationTimestamp // this annotation will automatically set the value of this field to the current timestamp when the entity is created.
    @Column(updatable = false) // this field will not be updated once it is set, it will only be set when the entity is created.
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    // this annotation will tell hibernate to store the enum value as a string in the database, instead of the default ordinal value.
    private BloodGroupType blood_group;

    // @Override
    // public String toString() {
    //     return "Patient [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", email=" + email + ", gender="
    //             + gender + "]";
    // }

    @OneToOne
    @JoinColumn(name = "patient_insurance_id", referencedColumnName = "id")
    private Insurance insurance; //Owning side of relationship

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments; //inverse side of relationship, as patient can have multiple appointments, so we need to use List here.

    // @OneToMany = we read this like - one patient to many appointments
    // Identify which is owning side and another would be inverse side use mappedBy for inverse
    // We identify owning side by checking if it can exist without the other entity
}
