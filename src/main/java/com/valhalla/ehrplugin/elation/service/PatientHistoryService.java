package com.valhalla.ehrplugin.elation.service;

import com.valhalla.ehrplugin.elation.dto.patientHistoryDto.PatientHistory;

import java.util.List;
import java.util.Optional;

public interface PatientHistoryService {
    List<PatientHistory> getAllPatientHistories();

    Optional<PatientHistory> getPatientHistoryById(Long id);

    void createPatientHistory(PatientHistory patientHistory);

    Optional<String> deletePatientHistory(Long id);

}
