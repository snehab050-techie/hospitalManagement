package com.sneha.springProject.hospitalManagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sneha.springProject.hospitalManagement.entity.BloodGroupType;
import com.sneha.springProject.hospitalManagement.entity.Patient;

import jakarta.transaction.Transactional;

// @Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByName(String name);

    List<Patient> findByEmail(String email);

    List<Patient> findByNameOrEmail(String name, String email);

    List<Patient> findByNameAndGender(String name, String gender);

    @Query("SELECT p FROM Patient p WHERE p.blood_group = ?1")
    List<Patient> findByBloodGroup(@Param("blood_group")BloodGroupType blood_group);

    @Query("SELECT p FROM Patient p WHERE p.birth_date > :birth_date")
    List<Patient> findByBornAfterDate(@Param("birth_date") LocalDate birth_date);

    @Query("SELECT p.blood_group,count(p) FROM Patient p GROUP BY p.blood_group")
    List<Object[]> countBloodGroupByGroupBy();

    @Query(value="select * from patient", nativeQuery =true)
    List<Patient> findAllPatients();

    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :name WHERE p.id = :id")
    int updateNameById(@Param("name") String name, @Param("id") Long id);
}