package com.valhalla.ehrplugin.elation.service;

import com.valhalla.ehrplugin.elation.dto.patientDto.Patient;
import com.valhalla.ehrplugin.elation.dto.patientDto.PatientRequest;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Object getAllPatients();

    Optional<Patient> getPatientById(Long id);

    Object createPatient(PatientRequest patientRequest);

    Optional<String> updatePatient(Long id, Patient updatedPatient);
}

