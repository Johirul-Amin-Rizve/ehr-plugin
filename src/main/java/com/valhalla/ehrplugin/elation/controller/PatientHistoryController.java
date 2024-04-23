package com.valhalla.ehrplugin.elation.controller;

import com.valhalla.ehrplugin.elation.dto.patientHistoryDto.PatientHistoryRequest;
import com.valhalla.ehrplugin.elation.service.PatientHistoryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/elation")
public class PatientHistoryController {
    private static final Logger logger = LoggerFactory.getLogger(PatientHistoryController.class);
    private final PatientHistoryService patientHistoryService;

    public PatientHistoryController(PatientHistoryService patientHistoryService) {
        this.patientHistoryService = patientHistoryService;
    }

    @GetMapping("/patient-histories")
    public ResponseEntity<Object> getPatientHistory() {
        logger.info("Received request to get patient history.");
        Object result = patientHistoryService.getAllPatientHistories();
        logger.info("Retrieved patient history successfully.");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/patient-histories")
    public ResponseEntity<Object> createPatientHistory(@Valid @RequestBody PatientHistoryRequest patientHistoryRequest) {
        logger.info("Creating a new patient history: {}", patientHistoryRequest);
        Object result = patientHistoryService.createPatientHistory(patientHistoryRequest);
        logger.info("Patient history created successfully: {}", patientHistoryRequest);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @DeleteMapping("/patient-histories/{id}")
    public ResponseEntity<Object> deletePatientHistory(@PathVariable Long id) {
        logger.info("Deleting patient history with ID: {}", id);
        Object result = patientHistoryService.deletePatientHistory(id);
        if (result != null) {
            logger.info("Patient history deleted successfully with ID: {}", id);
            return ResponseEntity.ok(result);
        } else {
            logger.warn("Patient history not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}
