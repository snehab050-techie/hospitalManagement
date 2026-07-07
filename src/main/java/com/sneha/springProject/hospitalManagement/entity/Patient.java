package com.sneha.springProject.hospitalManagement.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
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
    name = "patient_tbl",
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

    @Column(name = "patient_name", nullable = false,length = 40)
    // now this will create a coulmn named "patient_name" in the DB but the older column name "name" 
    // will still be there in the DB, so we need to drop the old column "name" from the DB and then 
    // run the application again to create the new column "patient_name" in the DB
    // but this is not a good practice, as we may  face data inconsistency issues, there comes data migration 
    // tools like Flyway and Liquibase, which can be used to manage the database schema changes in a more controlled way.
    private String name;

    // @ToString.Exclude
    private LocalDate birthDate;

    @Column(unique =true, nullable = false)
    private String email;

    private String gender;

    @CreationTimestamp // this annotation will automatically set the value of this field to the current timestamp when the entity is created.
    @Column(updatable = false) // this field will not be updated once it is set, it will only be set when the entity is created.
    private LocalDateTime createdAt;

    // @Override
    // public String toString() {
    //     return "Patient [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", email=" + email + ", gender="
    //             + gender + "]";
    // }

}
