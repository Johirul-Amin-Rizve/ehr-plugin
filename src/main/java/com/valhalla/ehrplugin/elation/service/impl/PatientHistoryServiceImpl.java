package com.valhalla.ehrplugin.elation.service.impl;

import com.valhalla.ehrplugin.elation.dto.patientHistoryDto.PatientHistory;
import com.valhalla.ehrplugin.elation.service.PatientHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PatientHistoryServiceImpl implements PatientHistoryService {
    private static final Logger logger = LoggerFactory.getLogger(PatientHistoryServiceImpl.class);
    private static List<PatientHistory> patientHistories = new ArrayList<>();

    @Override
    public List<PatientHistory> getAllPatientHistories() {
        logger.info("Fetching all patient histories");
        return patientHistories;
    }

    @Override
    public Optional<PatientHistory> getPatientHistoryById(Long id) {
        logger.info("Fetching patient history with ID: {}", id);
        return patientHistories.stream()
                .filter(patientHistory -> patientHistory.getId().equals(id))
                .findFirst();
    }

    @Override
    public void createPatientHistory(PatientHistory patientHistory) {
        logger.info("Creating patient history: {}", patientHistory);
        patientHistories.add(patientHistory);
    }

    @Override
    public Optional<String> deletePatientHistory(Long id) {
        logger.info("Deleting patient history with ID: {}", id);
        Iterator<PatientHistory> iterator = patientHistories.iterator();
        while (iterator.hasNext()) {
            PatientHistory patientHistory = iterator.next();
            if (patientHistory.getId().equals(id)) {
                iterator.remove();
                logger.info("Patient history deleted successfully with ID: {}", id);
                return Optional.of("Patient history deleted successfully");
            }
        }
        logger.warn("Patient history not found with ID: {}", id);
        return Optional.empty();
    }

}
