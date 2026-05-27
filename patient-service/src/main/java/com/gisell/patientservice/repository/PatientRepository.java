package com.gisell.patientservice.repository;

import com.gisell.patientservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    boolean existsAllByEmail(String email);
}
