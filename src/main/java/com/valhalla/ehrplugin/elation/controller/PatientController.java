package com.valhalla.ehrplugin.elation.controller;

import com.valhalla.ehrplugin.elation.dto.patientDto.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/elation")
public class PatientController {
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    private static List<Patient> patients = new ArrayList<>();

    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        logger.info("GET request received for all patients");
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable Long id) {
        logger.info("GET request received for patient with ID: {}", id);
        Optional<Patient> patientOptional = patients.stream()
                .filter(patient -> patient.getId().equals(id))
                .findFirst();
        if (patientOptional.isPresent()) {
            return new ResponseEntity<>(patientOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Patient not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/patients")
    public ResponseEntity<String> createPatient(@RequestBody Patient patient) {
        logger.info("POST request received for creating patient: {}", patient.getFirst_name());
        patients.add(patient);
        return new ResponseEntity<>("Patient created successfully", HttpStatus.CREATED);
    }

    @PatchMapping("/patients/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        logger.info("PATCH request received for updating patient with ID: {}", id);
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                // Update patient details
                patient.setFirst_name(updatedPatient.getFirst_name());
                patient.setMiddle_name(updatedPatient.getMiddle_name());
                patient.setLast_name(updatedPatient.getLast_name());
                patient.setActual_name(updatedPatient.getActual_name());
                patient.setGender_identity(updatedPatient.getGender_identity());
                patient.setLegal_gender_marker(updatedPatient.getLegal_gender_marker());
                patient.setPronouns(updatedPatient.getPronouns());
                patient.setSex(updatedPatient.getSex());
                patient.setSexual_orientation(updatedPatient.getSexual_orientation());
                patient.setPrimary_physician(updatedPatient.getPrimary_physician());
                patient.setCaregiver_practice(updatedPatient.getCaregiver_practice());
                patient.setDob(updatedPatient.getDob());
                patient.setSsn(updatedPatient.getSsn());
                patient.setRace(updatedPatient.getRace());
                patient.setPreferred_language(updatedPatient.getPreferred_language());
                patient.setEthnicity(updatedPatient.getEthnicity());
                patient.setNotes(updatedPatient.getNotes());
                patient.setVip(updatedPatient.isVip());
                patient.setAddress(updatedPatient.getAddress());
                patient.setPhones(updatedPatient.getPhones());
                patient.setEmails(updatedPatient.getEmails());
                patient.setGuarantor(updatedPatient.getGuarantor());
                patient.setInsurances(updatedPatient.getInsurances());
                patient.setDeleted_insurances(updatedPatient.getDeleted_insurances());
                patient.setTags(updatedPatient.getTags());
                patient.setPatient_status(updatedPatient.getPatient_status());
                patient.setPreference(updatedPatient.getPreference());
                patient.setEmergency_contact(updatedPatient.getEmergency_contact());
                patient.setPrimary_care_provider(updatedPatient.getPrimary_care_provider());
                patient.setPrimary_care_provider_npi(updatedPatient.getPrimary_care_provider_npi());
                patient.setPrevious_first_name(updatedPatient.getPrevious_first_name());
                patient.setPrevious_last_name(updatedPatient.getPrevious_last_name());
                patient.setPrevious_name(updatedPatient.getPrevious_name());
                patient.setMaster_patient(updatedPatient.getMaster_patient());
                patient.setEmployer(updatedPatient.getEmployer());
                patient.setConsents(updatedPatient.getConsents());
                patient.setMetadata(updatedPatient.getMetadata());
                patient.setCreated_date(updatedPatient.getCreated_date());
                patient.setDeleted_date(updatedPatient.getDeleted_date());
                patient.setMerged_into_chart(updatedPatient.getMerged_into_chart());
                return new ResponseEntity<>("Patient updated successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Patient not found with ID: " + id, HttpStatus.NOT_FOUND);
    }
}
