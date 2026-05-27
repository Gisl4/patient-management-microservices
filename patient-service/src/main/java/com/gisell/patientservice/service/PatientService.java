package com.gisell.patientservice.service;

import com.gisell.patientservice.dto.PatientRequestDTO;
import com.gisell.patientservice.dto.PatientResponseDTO;
import com.gisell.patientservice.exception.EmailAlreadyExistsException;
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

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsAllByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email already"
                    + "already exists" + patientRequestDTO.getEmail());
        }

        Patient newPatient = patientRepository.save(
                PatientMapper.toModel(patientRequestDTO));

        return PatientMapper.toDTO(newPatient);
    }
}
