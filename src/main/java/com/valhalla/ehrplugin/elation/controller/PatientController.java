package com.valhalla.ehrplugin.elation.controller;

import com.valhalla.ehrplugin.elation.dto.patientDto.Patient;
import com.valhalla.ehrplugin.elation.dto.patientDto.PatientRequest;
import com.valhalla.ehrplugin.elation.dto.patientHistoryDto.PatientHistoryRequest;
import com.valhalla.ehrplugin.elation.service.PatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/elation")
public class PatientController {
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public ResponseEntity<Object> getAllPatients() {
        logger.info("Received request to get patient.");
        Object result = patientService.getAllPatients();
        logger.info("Retrieved patient successfully.");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/patients")
    public ResponseEntity<Object> createPatient(@Valid @RequestBody PatientRequest patientRequest) {
        logger.info("Creating a new patient: {}", patientRequest);
        Object result = patientService.createPatient(patientRequest);
        logger.info("Patient created successfully: {}", patientRequest);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping("/patients/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        logger.info("Updating patient with ID: {}", id);
        Optional<String> result = patientService.updatePatient(id, updatedPatient);
        if (result.isPresent()) {
            logger.info("Patient updated successfully with ID: {}", id);
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            logger.warn("Patient not found with ID: {}", id);
            return new ResponseEntity<>("Patient not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
