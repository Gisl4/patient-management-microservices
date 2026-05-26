package com.gisell.patientservice.service;

import com.gisell.patientservice.dto.PatientResponseDTO;
import com.gisell.patientservice.mapper.PatientMapper;
import com.gisell.patientservice.model.Patient;
import com.gisell.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients (){
        List<Patient> patients = patientRepository.findAll();

        return patients.stream()
                .map(PatientMapper::toDTO).toList();
    }
}
