package com.valhalla.ehrplugin.elation.controller;

import com.valhalla.ehrplugin.elation.dto.patientDto.Patient;
import com.valhalla.ehrplugin.elation.service.PatientService;
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
    public ResponseEntity<List<Patient>> getAllPatients() {
        logger.info("Fetching all patients");
        List<Patient> patients = patientService.getAllPatients();
        logger.info("Retrieved {} patients", patients.size());
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        logger.info("Fetching patient with ID: {}", id);
        Optional<Patient> patientOptional = patientService.getPatientById(id);
        if (patientOptional.isPresent()) {
            logger.info("Patient found with ID: {}", id);
            return new ResponseEntity<>(patientOptional.get(), HttpStatus.OK);
        } else {
            logger.warn("Patient not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/patients")
    public ResponseEntity<String> createPatient(@RequestBody Patient patient) {
        logger.info("Creating a new patient: {}", patient);
        patientService.createPatient(patient);
        logger.info("Patient created successfully with ID: {}", patient.getId());
        return new ResponseEntity<>("Patient created successfully", HttpStatus.CREATED);
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
