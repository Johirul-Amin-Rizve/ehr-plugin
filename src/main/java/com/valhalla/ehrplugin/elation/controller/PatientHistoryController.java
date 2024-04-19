package com.valhalla.ehrplugin.elation.controller;

import com.valhalla.ehrplugin.elation.dto.patientHistoryDto.PatientHistory;
import com.valhalla.ehrplugin.elation.service.PatientHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/elation")
public class PatientHistoryController {
    private static final Logger logger = LoggerFactory.getLogger(PatientHistoryController.class);
    private final PatientHistoryService patientHistoryService;

    public PatientHistoryController(PatientHistoryService patientHistoryService) {
        this.patientHistoryService = patientHistoryService;
    }

    @GetMapping("/patient-histories")
    public ResponseEntity<List<PatientHistory>> getAllPatientHistories() {
        logger.info("Fetching all patient histories");
        List<PatientHistory> patientHistories = patientHistoryService.getAllPatientHistories();
        logger.info("Retrieved {} patient histories", patientHistories.size());
        return new ResponseEntity<>(patientHistories, HttpStatus.OK);
    }

    @GetMapping("/patient-histories/{id}")
    public ResponseEntity<PatientHistory> getPatientHistoryById(@PathVariable Long id) {
        logger.info("Fetching patient history with ID: {}", id);
        Optional<PatientHistory> patientHistoryOptional = patientHistoryService.getPatientHistoryById(id);
        if (patientHistoryOptional.isPresent()) {
            logger.info("Patient history found with ID: {}", id);
            return new ResponseEntity<>(patientHistoryOptional.get(), HttpStatus.OK);
        } else {
            logger.warn("Patient history not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/patient-histories")
    public ResponseEntity<String> createPatientHistory(@RequestBody PatientHistory patientHistory) {
        logger.info("Creating a new patient history: {}", patientHistory);
        patientHistoryService.createPatientHistory(patientHistory);
        logger.info("Patient history created successfully with ID: {}", patientHistory.getId());
        return new ResponseEntity<>("Patient history created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/patient-histories/{id}")
    public ResponseEntity<String> deletePatientHistory(@PathVariable Long id) {
        logger.info("Deleting patient history with ID: {}", id);
        Optional<String> result = patientHistoryService.deletePatientHistory(id);
        if (result.isPresent()) {
            logger.info("Patient history deleted successfully with ID: {}", id);
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            logger.warn("Patient history not found with ID: {}", id);
            return new ResponseEntity<>("Patient history not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
