package com.valhalla.ehrplugin.elation.service.impl;

import com.valhalla.ehrplugin.elation.dto.visitNoteDto.VisitNoteRequest;
import com.valhalla.ehrplugin.elation.service.VisitNoteService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
public class VisitNoteServiceImpl implements VisitNoteService {

    private static final Logger logger = LoggerFactory.getLogger(VisitNoteServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpServletRequest request;

    @Value("${elation.api.baseurl}")
    private String baseUrl;

    @Override
    public Object getAllVisitNotes() {
        String authorizationToken = request.getHeader("Authorization");
        logger.info("Received request to fetch visit notes. Authorization token: {}", authorizationToken);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationToken);
        headers.set("accept", "application/json");

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Object> response = restTemplate.exchange(
                    baseUrl + "/visit_notes/",
                    HttpMethod.GET,
                    requestEntity,
                    Object.class
            );

            int statusCodeValue = response.getStatusCodeValue();
            HttpStatus statusCode = (HttpStatus) response.getStatusCode();
            logger.info("Received response from API with status code: {} - {}", statusCodeValue, statusCode);

            if (statusCode.is2xxSuccessful()) {
                Object responseBody = response.getBody();
                logger.info("Retrieved visit notes from the API.");
                return responseBody;
            } else {
                logger.error("Failed to retrieve visit notes. Response status code: {}", statusCodeValue);
                return new Object();
            }
        } catch (RestClientException ex) {
            logger.error("Error occurred while fetching visit notes: {}", ex.getMessage(), ex);
            return new Object();
        }
    }

    @Override
    public Object createVisitNote(VisitNoteRequest visitNoteRequest) {
        String authorizationToken = request.getHeader("Authorization");
        logger.info("Received request to create visit note. Authorization token: {}", authorizationToken);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationToken);
        headers.set("accept", "application/json");
        headers.set("Content-Type", "application/json");

        HttpEntity<VisitNoteRequest> requestEntity = new HttpEntity<>(visitNoteRequest, headers);

        try {
            ResponseEntity<Object> response = restTemplate.exchange(
                    baseUrl + "/visit_notes/",
                    HttpMethod.POST,
                    requestEntity,
                    Object.class
            );

            HttpStatus statusCode = (HttpStatus) response.getStatusCode();
            logger.info("Received response from API with status code: {}", statusCode);

            if (statusCode.is2xxSuccessful()) {
                Object responseBody = response.getBody();
                logger.info("Visit note created successfully.");
                return responseBody;
            } else {
                logger.error("Failed to create visit note. Response status code: {}", statusCode);
                return new Object(); // You may choose to handle this differently
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            logger.error("Error occurred while creating visit note: {}", ex.getMessage(), ex);
            return new Object(); // You may choose to handle this differently
        } catch (RestClientException ex) {
            logger.error("General RestClientException occurred: {}", ex.getMessage(), ex);
            return new Object(); // You may choose to handle this differently
        }
    }

    @Override
    public Object deleteVisitNote(@PathVariable Long id) {
        String authorizationToken = request.getHeader("Authorization");
        logger.info("Received request to delete visit note with ID {}. Authorization token: {}", id, authorizationToken);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationToken);
        headers.set("accept", "application/json");

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Void> response = restTemplate.exchange(
                    baseUrl + "/visit_notes/" + id,
                    HttpMethod.DELETE,
                    requestEntity,
                    Void.class
            );

            int statusCodeValue = response.getStatusCodeValue();
            HttpStatus statusCode = (HttpStatus) response.getStatusCode();
            logger.info("Received response from API with status code: {} - {}", statusCodeValue, statusCode);

            if (statusCode.is2xxSuccessful()) {
                logger.info("Visit note deleted successfully with ID: {}", id);
                return null; // Or any response you want to return upon successful deletion
            } else {
                logger.error("Failed to delete visit note with ID {}. Response status code: {}", id, statusCodeValue);
                return new Object();
            }
        } catch (RestClientException ex) {
            logger.error("Error occurred while deleting visit note with ID {}: {}", id, ex.getMessage(), ex);
            return new Object();
        }
    }

}
