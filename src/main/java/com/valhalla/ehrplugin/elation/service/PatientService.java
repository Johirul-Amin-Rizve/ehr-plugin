package com.valhalla.ehrplugin.elation.service;

import com.valhalla.ehrplugin.elation.dto.patientDto.Patient;
import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<Patient> getAllPatients();

    Optional<Patient> getPatientById(Long id);

    void createPatient(Patient patient);

    Optional<String> updatePatient(Long id, Patient updatedPatient);
}

