package com.valhalla.ehrplugin.elation.service.impl;

import com.valhalla.ehrplugin.elation.dto.appointmentDto.Appointment;
import com.valhalla.ehrplugin.elation.dto.appointmentDto.AppointmentRequest;
import com.valhalla.ehrplugin.elation.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpServletRequest request;

    @Value("${elation.api.baseurl}")
    private String baseUrl;

    @Override
    public Object getAllAppointments() {
        String authorizationToken = request.getHeader("Authorization");
        logger.info("Received request to fetch appointments. Authorization token: {}", authorizationToken);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationToken);
        headers.set("accept", "application/json");

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Object> response = restTemplate.exchange(
                    baseUrl + "/appointments/",
                    HttpMethod.GET,
                    requestEntity,
                    Object.class
            );

            int statusCodeValue = response.getStatusCodeValue();
            HttpStatus statusCode = (HttpStatus) response.getStatusCode();
            logger.info("Received response from API with status code: {} - {}", statusCodeValue, statusCode);

            if (statusCode.is2xxSuccessful()) {
                Object responseBody = response.getBody();
                logger.info("Retrieved appointments from the API.");
                return responseBody;
            } else {
                logger.error("Failed to retrieve appointments. Response status code: {}", statusCodeValue);
                return new Object();
            }
        } catch (RestClientException ex) {
            logger.error("Error occurred while fetching appointments: {}", ex.getMessage(), ex);
            return new Object();
        }
    }

    @Override
    public Optional<Object> getAppointmentById(Long id) {
        String authorizationToken = request.getHeader("Authorization");
        logger.info("Received request to fetch appointment by ID {}. Authorization token: {}", id, authorizationToken);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationToken);
        headers.set("accept", "application/json");

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Object> response = restTemplate.exchange(
                    baseUrl + "/appointments/" + id,
                    HttpMethod.GET,
                    requestEntity,
                    Object.class
            );

            int statusCodeValue = response.getStatusCodeValue();
            HttpStatus statusCode = (HttpStatus) response.getStatusCode();
            logger.info("Received response from API with status code: {} - {}", statusCodeValue, statusCode);

            if (statusCode.is2xxSuccessful()) {
                Object responseBody = response.getBody();
                logger.info("Retrieved appointment with ID {} from the API.", id);
                return Optional.ofNullable(responseBody);
            } else if (statusCode == HttpStatus.NOT_FOUND) {
                logger.warn("Appointment with ID {} not found.", id);
                return Optional.empty();
            } else {
                logger.error("Failed to retrieve appointment with ID {}. Response status code: {}", id, statusCodeValue);
                return Optional.empty();
            }
        } catch (RestClientException ex) {
            logger.error("Error occurred while fetching appointment with ID {}: {}", id, ex.getMessage(), ex);
            return Optional.empty();
        }
    }

    @Override
    public Object createAppointment(AppointmentRequest appointmentRequest) {
        String authorizationToken = request.getHeader("Authorization");
        logger.info("Received request to create appointment. Authorization token: {}", authorizationToken);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationToken);
        headers.set("accept", "application/json");
        headers.set("Content-Type", "application/json");

        HttpEntity<AppointmentRequest> requestEntity = new HttpEntity<>(appointmentRequest, headers);

        try {
            ResponseEntity<Object> response = restTemplate.exchange(
                    baseUrl + "/appointments/",
                    HttpMethod.POST,
                    requestEntity,
                    Object.class
            );

            HttpStatus statusCode = (HttpStatus) response.getStatusCode();
            logger.info("Received response from API with status code: {}", statusCode);

            if (statusCode.is2xxSuccessful()) {
                Object responseBody = response.getBody();
                logger.info("Appointment created successfully.");
                return responseBody;
            } else {
                logger.error("Failed to create appointment. Response status code: {}", statusCode);
                return new Object(); // You may choose to handle this differently
            }
        } catch (HttpClientErrorException ex) {
            logger.error("Error occurred while creating appointment: {}", ex.getMessage(), ex);
            return new Object(); // You may choose to handle this differently
        } catch (RestClientException ex) {
            logger.error("General RestClientException occurred: {}", ex.getMessage(), ex);
            return new Object(); // You may choose to handle this differently
        }
    }

    @Override
    public Object updateAppointment(Long id, Appointment appointment) {
        String authorizationToken = request.getHeader("Authorization");
        logger.info("Received request to update appointment with ID {}. Authorization token: {}", id, authorizationToken);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationToken);
        headers.set("Content-Type", "application/json");

        HttpEntity<Appointment> requestEntity = new HttpEntity<>(appointment, headers);

        try {
            ResponseEntity<Object> response = restTemplate.exchange(
                    baseUrl + "/appointments/" + id,
                    HttpMethod.PUT,
                    requestEntity,
                    Object.class
            );

            int statusCodeValue = response.getStatusCodeValue();
            HttpStatus statusCode = (HttpStatus) response.getStatusCode();
            logger.info("Received response from API with status code: {} - {}", statusCodeValue, statusCode);

            if (statusCode.is2xxSuccessful()) {
                Object responseBody = response.getBody();
                logger.info("Appointment with ID {} updated successfully.", id);
                return responseBody;
            } else if (statusCode == HttpStatus.NOT_FOUND) {
                logger.error("Appointment with ID {} not found.", id);
                return null;
            } else {
                logger.error("Failed to update appointment with ID {}. Response status code: {}", id, statusCodeValue);
                return null;
            }
        } catch (RestClientException ex) {
            logger.error("Error occurred while updating appointment with ID {}: {}", id, ex.getMessage(), ex);
            return null;
        }
    }
}
