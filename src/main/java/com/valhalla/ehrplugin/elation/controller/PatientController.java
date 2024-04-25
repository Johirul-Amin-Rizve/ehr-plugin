package com.valhalla.ehrplugin.elation.controller;

import com.valhalla.ehrplugin.elation.dto.patientDto.PatientRequest;
import com.valhalla.ehrplugin.elation.service.PatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Object> updatePatient(@PathVariable Long id, @Valid @RequestBody PatientRequest patientRequest) {
        logger.info("Updating patient with ID {}: {}", id, patientRequest);

        // Check if patient with given ID exists
        Optional<Object> existingPatient = patientService.getPatientById(id);
        if (existingPatient.isEmpty()) {
            logger.error("Patient with ID {} not found", id);
            return new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
        }

        // Delegate update operation to the service class
        Object result = patientService.updatePatient(id, patientRequest);
        logger.info("Patient with ID {} updated successfully: {}", id, patientRequest);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
