package com.valhalla.ehrplugin.elation.service.impl;

import com.valhalla.ehrplugin.elation.dto.patientDto.PatientRequest;
import com.valhalla.ehrplugin.elation.service.PatientService;
import com.valhalla.ehrplugin.elation.dto.patientDto.Patient;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    private final HttpServletRequest request;
    private final RestClient restClient;

    @Value("${elation.api.baseurl}")
    private String baseUrl;

    @Override
    public Object getAllPatients() {
        String authorizationToken = request.getHeader("Authorization");
        logger.info("Received request to fetch patients. Authorization token: {}", authorizationToken);

        try {
            ResponseEntity<Object> response = restClient.get()
                    .uri(baseUrl + "/patients")
                    .accept(MediaType.APPLICATION_JSON)
                    .header("Authorization", authorizationToken)
                    .retrieve()
                    .toEntity(Object.class);

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
    public Optional<Object> getPatientById(Long id) {
        String authorizationToken = request.getHeader("Authorization");
        logger.info("Received request to fetch patient by ID {}. Authorization token: {}", id, authorizationToken);

        try {
            ResponseEntity<Object> response = restClient.get()
                    .uri(baseUrl + "/patients/" + id)
                    .accept(MediaType.APPLICATION_JSON)
                    .header("Authorization", authorizationToken)
                    .retrieve()
                    .toEntity(Object.class);

            int statusCodeValue = response.getStatusCodeValue();
            HttpStatus statusCode = (HttpStatus) response.getStatusCode();
            logger.info("Received response from API with status code: {} - {}", statusCodeValue, statusCode);

            if (statusCode.is2xxSuccessful()) {
                Object responseBody = response.getBody();
                logger.info("Retrieved patient with ID {} from the API.", id);
                return Optional.ofNullable(responseBody);
            } else if (statusCode == HttpStatus.NOT_FOUND) {
                logger.warn("Patient with ID {} not found.", id);
                return Optional.empty();
            } else {
                logger.error("Failed to retrieve patient with ID {}. Response status code: {}", id, statusCodeValue);
                return Optional.empty();
            }
        } catch (RestClientException ex) {
            logger.error("Error occurred while fetching patient with ID {}: {}", id, ex.getMessage(), ex);
            return Optional.empty();
        }
    }

    @Override
    public Object createPatient(PatientRequest patientRequest) {
        String authorizationToken = request.getHeader("Authorization");
        logger.info("Received request to create patient: {}", patientRequest);


        try {
            ResponseEntity<Object> response = restClient.post()
                    .uri(baseUrl + "/patients/")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", authorizationToken)
                    .body(patientRequest)
                    .retrieve()
                    .toEntity(Object.class);

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
    public Object updatePatient(Long id, Patient patient) {
        String authorizationToken = request.getHeader("Authorization");
        logger.info("Received request to update patient: {}", patient);

        try {
            ResponseEntity<Object> response = restClient.put()
                    .uri(baseUrl + "/patients/" + id)
                    .headers(headers -> {
                        headers.set("Authorization", authorizationToken);
                        headers.set("Content-Type", "application/json");
                    })
                    .body(patient)
                    .retrieve()
                    .toEntity(Object.class);

            int statusCodeValue = response.getStatusCodeValue();
            HttpStatus statusCode = (HttpStatus) response.getStatusCode();
            logger.info("Received response from API with status code: {} - {}", statusCodeValue, statusCode);

            if (statusCode.is2xxSuccessful()) {
                Object responseBody = response.getBody();
                logger.info("Patient with ID {} updated successfully.", id);
                return responseBody;
            } else if (statusCode == HttpStatus.NOT_FOUND) {
                logger.error("Patient with ID {} not found.", id);
                return null;
            } else {
                logger.error("Failed to update patient with ID {}. Response status code: {}", id, statusCodeValue);
                return null;
            }
        } catch (RestClientException ex) {
            logger.error("Error occurred while updating patient with ID {}: {}", id, ex.getMessage(), ex);
            return null;
        }
    }

}

