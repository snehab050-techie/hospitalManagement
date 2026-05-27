package com.sneha.springProject.hospitalManagement.service;

import org.springframework.stereotype.Service;

import com.sneha.springProject.hospitalManagement.entity.Patient;
import com.sneha.springProject.hospitalManagement.repository.PatientRepository;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor
public class PatientService {
    
        private final PatientRepository patientRepository;

        @Transactional 
        // transactional ensures that the same persistence context is used for the entire method execution, so that the first level cache is maintained and both p1 and p2 will refer to the same object in the memory.
        // if we remove @Transactional, then each call to findById will create a new persistence context and both p1 and p2 will refer to different objects in the memory, so it will return false.
        // @Transactional is used to manage the transaction boundaries and ensure that the operations within the method are executed within a single transaction.
        // it also ensures that the persistence context is properly managed and that the first level cache is maintained, which allows for efficient retrieval of entities and reduces the number of database queries.
        // in this case, it allows us to demonstrate the first level cache of the persistence context, where both p1 and p2 refer to the same object in the memory, resulting in true when compared with == operator.
        // in simple words, for a session, if we fetch the same entity multiple times, it will return the same reference of that entity from the first level cache, which is maintained by the persistence context.
        public Patient getPatientById(Long id) {
            Patient p1 = patientRepository.findById(id).orElseThrow();
            Patient p2 = patientRepository.findById(id).orElseThrow();

            System.out.println(p1 == p2); // true 
            //because of first level cache of persistence context, 
            // both p1 and p2 are same reference of the same object in the memory. 
            // so it will return true.

            p1.setName("Julie");
            // no need to call save method, because of transactional, 
            // the persistence context will automatically detect the change in the entity and will update the database when the transaction is committed.

            return p1;
        }

        
}
