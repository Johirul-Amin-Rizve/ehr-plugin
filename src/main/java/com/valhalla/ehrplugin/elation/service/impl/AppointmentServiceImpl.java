package com.valhalla.ehrplugin.elation.service.impl;

import com.valhalla.ehrplugin.elation.dto.appointmentDto.Appointment;
import com.valhalla.ehrplugin.elation.dto.appointmentDto.AppointmentRequest;
import com.valhalla.ehrplugin.elation.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    private final HttpServletRequest request;
    private final RestClient restClient;
    private final KafkaProducerService kafkaProducerService;

    @Value("${elation.api.baseurl}")
    private String baseUrl;

    @Override
    public Object getAllAppointments() {
        String authorizationToken = request.getHeader("Authorization");
        logger.info("Received request to fetch appointments. Authorization token: {}", authorizationToken);

        try {
            ResponseEntity<Object> response = restClient.get()
                    .uri(baseUrl + "/appointments")
                    .accept(MediaType.APPLICATION_JSON)
                    .header("Authorization", authorizationToken)
                    .retrieve()
                    .toEntity(Object.class);

            int statusCodeValue = response.getStatusCode().value();
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

        try {
            ResponseEntity<Object> response = restClient.get()
                    .uri(baseUrl + "/appointments/" + id)
                    .accept(MediaType.APPLICATION_JSON)
                    .header("Authorization", authorizationToken)
                    .retrieve()
                    .toEntity(Object.class);

            int statusCodeValue = response.getStatusCode().value();
            HttpStatus statusCode = (HttpStatus) response.getStatusCode();
            logger.info("Received response from API with status code: {} - {}", statusCodeValue, statusCode);

            if (statusCode.is2xxSuccessful()) {
                Object responseBody = response.getBody();
                logger.info("Retrieved appointment with ID {} from the API.", id);
                return Optional.ofNullable(responseBody);
            } else if (statusCode.equals(HttpStatus.NOT_FOUND)) {
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
        logger.info("Received request to create appointment: {}", appointmentRequest);

        try {
            ResponseEntity<Object> response = restClient.post()
                    .uri(baseUrl + "/appointments/")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", authorizationToken)
                    .body(appointmentRequest)
                    .retrieve()
                    .toEntity(Object.class);

            HttpStatus statusCode = (HttpStatus) response.getStatusCode();
            logger.info("Received response from API with status code: {}", statusCode);

            if (statusCode.is2xxSuccessful()) {
                Object responseBody = response.getBody();
                logger.info("Appointment created successfully.");
                // appointmentRequest sent to kafka producer service
                kafkaProducerService.sendMessage(appointmentRequest);
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

        try {
            ResponseEntity<Object> response = restClient.put()
                    .uri(baseUrl + "/appointments/" + id)
                    .headers(headers -> {
                        headers.set("Authorization", authorizationToken);
                        headers.set("Content-Type", "application/json");
                    })
                    .body(appointment)
                    .retrieve()
                    .toEntity(Object.class);

            int statusCodeValue = response.getStatusCode().value();
            HttpStatus statusCode = (HttpStatus) response.getStatusCode();
            logger.info("Received response from API with status code: {} - {}", statusCodeValue, statusCode);

            if (statusCode.is2xxSuccessful()) {
                Object responseBody = response.getBody();
                logger.info("Appointment with ID {} updated successfully.", id);
                return responseBody;
            } else if (statusCode.equals(HttpStatus.NOT_FOUND)) {
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
