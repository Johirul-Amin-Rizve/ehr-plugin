package com.valhalla.ehrplugin.elation.service.impl;

import com.valhalla.ehrplugin.elation.service.PatientService;
import com.valhalla.ehrplugin.elation.dto.patientDto.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);
    private static List<Patient> patients = new ArrayList<>();

    @Override
    public List<Patient> getAllPatients() {
        logger.info("Fetching all patients");
        return patients;
    }

    @Override
    public Optional<Patient> getPatientById(Long id) {
        logger.info("Fetching patient with ID: {}", id);
        return patients.stream()
                .filter(patient -> patient.getId().equals(id))
                .findFirst();
    }

    @Override
    public void createPatient(Patient patient) {
        logger.info("Creating patient: {}", patient.getFirst_name());
        patients.add(patient);
    }

    @Override
    public Optional<String> updatePatient(Long id, Patient updatedPatient) {
        logger.info("Updating patient with ID: {}", id);
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
                return Optional.of("Patient updated successfully");
            }
        }
        return Optional.empty();
    }
}

