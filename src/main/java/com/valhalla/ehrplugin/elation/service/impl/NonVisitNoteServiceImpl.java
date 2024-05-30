package com.valhalla.ehrplugin.elation.service.impl;

import com.valhalla.ehrplugin.elation.dto.nonVisitNoteDto.NonVisitNoteRequest;
import com.valhalla.ehrplugin.elation.service.NonVisitNoteService;
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


@Service
public class NonVisitNoteServiceImpl implements NonVisitNoteService {

    private static final Logger logger = LoggerFactory.getLogger(NonVisitNoteServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpServletRequest request;

    @Value("${elation.api.baseurl}")
    private String baseUrl;

    @Override
    public Object createNonVisitNote(NonVisitNoteRequest nonVisitNoteRequest) {
        String authorizationToken = request.getHeader("Authorization");
        logger.info("Received request to create non visit note: {}", nonVisitNoteRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationToken);
        headers.set("accept", "application/json");
        headers.set("Content-Type", "application/json");

        HttpEntity<NonVisitNoteRequest> requestEntity = new HttpEntity<>(nonVisitNoteRequest, headers);

        try {
            ResponseEntity<Object> response = restTemplate.exchange(
                    baseUrl + "/non_visit_notes/",
                    HttpMethod.POST,
                    requestEntity,
                    Object.class
            );

            HttpStatus statusCode = (HttpStatus) response.getStatusCode();
            logger.info("Received response from API with status code: {}", statusCode);

            if (statusCode.is2xxSuccessful()) {
                Object responseBody = response.getBody();
                logger.info("Non visit note created successfully.");
                return responseBody;
            } else {
                logger.error("Failed to create non visit note. Response status code: {}", statusCode);
                return new Object(); // You may choose to handle this differently
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            logger.error("Error occurred while creating non visit note: {}", ex.getMessage(), ex);
            return new Object(); // You may choose to handle this differently
        } catch (RestClientException ex) {
            logger.error("General RestClientException occurred: {}", ex.getMessage(), ex);
            return new Object(); // You may choose to handle this differently
        }
    }
}
