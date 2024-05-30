package com.valhalla.ehrplugin.elation.service;

import com.valhalla.ehrplugin.elation.dto.patientDto.Patient;
import com.valhalla.ehrplugin.elation.dto.patientDto.PatientRequest;

import java.util.Optional;

public interface PatientService {
    Object getAllPatients();

    Optional<Object> getPatientById(Long id);

    Object createPatient(PatientRequest patientRequest);

    Object updatePatient(Long id, Patient patient);
}

