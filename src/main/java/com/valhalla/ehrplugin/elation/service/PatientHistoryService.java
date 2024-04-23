package com.valhalla.ehrplugin.elation.service;

import com.valhalla.ehrplugin.elation.dto.patientHistoryDto.PatientHistoryRequest;

import java.util.Optional;

public interface PatientHistoryService {
    Object getAllPatientHistories();

    Object createPatientHistory(PatientHistoryRequest patientHistory);

    Object deletePatientHistory(Long id);

}
