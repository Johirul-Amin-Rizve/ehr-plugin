package com.valhalla.ehrplugin.elation.service.impl;

import com.valhalla.ehrplugin.elation.dto.patientHistoryDto.PatientHistoryRequest;
import com.valhalla.ehrplugin.elation.service.PatientHistoryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.*;


@Service
@RequiredArgsConstructor
public class PatientHistoryServiceImpl implements PatientHistoryService {

    private static final Logger logger = LoggerFactory.getLogger(PatientHistoryServiceImpl.class);

    private final HttpServletRequest request;
    private final RestClient restClient;

    @Value("${elation.api.baseurl}")
    private String baseUrl;

    @Override
    public Object getAllPatientHistories() {
        String authorizationToken = request.getHeader("Authorization");
        logger.info("Received request to fetch patient histories. Authorization token: {}", authorizationToken);

        try {
            ResponseEntity<Object> response = restClient.get()
                    .uri(baseUrl + "/histories")
                    .accept(MediaType.APPLICATION_JSON)
                    .header("Authorization", authorizationToken)
                    .retrieve()
                    .toEntity(Object.class);

            int statusCodeValue = response.getStatusCodeValue();
            HttpStatus statusCode = (HttpStatus) response.getStatusCode();
            logger.info("Received response from API with status code: {} - {}", statusCodeValue, statusCode);

            if (statusCode.is2xxSuccessful()) {
                Object responseBody = response.getBody();
                logger.info("Retrieved patient histories from the API.");
                return responseBody;
            } else {
                logger.error("Failed to retrieve patient histories. Response status code: {}", statusCodeValue);
                return new Object();
            }
        } catch (RestClientException ex) {
            logger.error("Error occurred while fetching patient histories: {}", ex.getMessage(), ex);
            return new Object();
        }
    }

    @Override
    public Object createPatientHistory(PatientHistoryRequest patientHistoryRequest) {
        String authorizationToken = request.getHeader("Authorization");
        logger.info("Received request to create patient history: {}", patientHistoryRequest);

        try {
            ResponseEntity<Object> response = restClient.post()
                    .uri(baseUrl + "/histories/")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", authorizationToken)
                    .body(patientHistoryRequest)
                    .retrieve()
                    .toEntity(Object.class);

            HttpStatus statusCode = (HttpStatus) response.getStatusCode();
            logger.info("Received response from API with status code: {}", statusCode);

            if (statusCode.is2xxSuccessful()) {
                Object responseBody = response.getBody();
                logger.info("Patient history created successfully.");
                return responseBody;
            } else {
                logger.error("Failed to create patient history. Response status code: {}", statusCode);
                return new Object(); // You may choose to handle this differently
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            logger.error("Error occurred while creating patient history: {}", ex.getMessage(), ex);
            return new Object(); // You may choose to handle this differently
        } catch (RestClientException ex) {
            logger.error("General RestClientException occurred: {}", ex.getMessage(), ex);
            return new Object(); // You may choose to handle this differently
        }
    }

    @Override
    public Object deletePatientHistory(@PathVariable Long id) {
        String authorizationToken = request.getHeader("Authorization");
        logger.info("Received request to delete patient history with ID {}. Authorization token: {}", id, authorizationToken);

        try {
            ResponseEntity<Void> response = restClient.delete()
                    .uri(baseUrl + "/histories/" + id)
                    .header("Authorization", authorizationToken)
                    .retrieve()
                    .toEntity(Void.class);

            int statusCodeValue = response.getStatusCodeValue();
            HttpStatus statusCode = (HttpStatus) response.getStatusCode();
            logger.info("Received response from API with status code: {} - {}", statusCodeValue, statusCode);

            if (statusCode.is2xxSuccessful()) {
                logger.info("Patient history deleted successfully with ID: {}", id);
                return null; // Or any response you want to return upon successful deletion
            } else {
                logger.error("Failed to delete patient history with ID {}. Response status code: {}", id, statusCodeValue);
                return new Object();
            }
        } catch (RestClientException ex) {
            logger.error("Error occurred while deleting patient history with ID {}: {}", id, ex.getMessage(), ex);
            return new Object();
        }
    }

}
