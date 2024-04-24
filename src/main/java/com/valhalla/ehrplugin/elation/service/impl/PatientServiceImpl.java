package com.valhalla.ehrplugin.elation.service.impl;

import com.valhalla.ehrplugin.elation.dto.patientDto.PatientRequest;
import com.valhalla.ehrplugin.elation.service.PatientService;
import com.valhalla.ehrplugin.elation.dto.patientDto.Patient;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpServletRequest request;

    @Value("${elation.api.baseurl}")
    private String baseUrl;

    private static List<Patient> patients = new ArrayList<>();

    @Override
    public Object getAllPatients() {
        String authorizationToken = request.getHeader("Authorization");
        logger.info("Received request to fetch patients. Authorization token: {}", authorizationToken);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationToken);
        headers.set("accept", "application/json");

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Object> response = restTemplate.exchange(
                    baseUrl + "/patients/",
                    HttpMethod.GET,
                    requestEntity,
                    Object.class
            );

            int statusCodeValue = response.getStatusCodeValue();
            HttpStatus statusCode = (HttpStatus) response.getStatusCode();
            logger.info("Received response from API with status code: {} - {}", statusCodeValue, statusCode);

            if (statusCode.is2xxSuccessful()) {
                Object responseBody = response.getBody();
                logger.info("Retrieved patients from the API.");
                return responseBody;
            } else {
                logger.error("Failed to retrieve patients. Response status code: {}", statusCodeValue);
                return new Object();
            }
        } catch (RestClientException ex) {
            logger.error("Error occurred while fetching patients: {}", ex.getMessage(), ex);
            return new Object();
        }
    }

    @Override
    public Optional<Patient> getPatientById(Long id) {
        logger.info("Fetching patient with ID: {}", id);
        return patients.stream()
                .filter(patient -> patient.getId().equals(id))
                .findFirst();
    }

    @Override
    public Object createPatient(PatientRequest patientRequest) {
        String authorizationToken = request.getHeader("Authorization");
        logger.info("Received request to create patient. Authorization token: {}", authorizationToken);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationToken);
        headers.set("accept", "application/json");
        headers.set("Content-Type", "application/json");

        HttpEntity<PatientRequest> requestEntity = new HttpEntity<>(patientRequest, headers);

        try {
            ResponseEntity<Object> response = restTemplate.exchange(
                    baseUrl + "/patients/",
                    HttpMethod.POST,
                    requestEntity,
                    Object.class
            );

            HttpStatus statusCode = (HttpStatus) response.getStatusCode();
            logger.info("Received response from API with status code: {}", statusCode);

            if (statusCode.is2xxSuccessful()) {
                Object responseBody = response.getBody();
                logger.info("Patient created successfully.");
                return responseBody;
            } else {
                logger.error("Failed to create patient. Response status code: {}", statusCode);
                return new Object(); // You may choose to handle this differently
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            logger.error("Error occurred while creating patient: {}", ex.getMessage(), ex);
            return new Object(); // You may choose to handle this differently
        } catch (RestClientException ex) {
            logger.error("General RestClientException occurred: {}", ex.getMessage(), ex);
            return new Object(); // You may choose to handle this differently
        }
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

