package com.valhalla.ehrplugin.elation.service.impl;

import com.valhalla.ehrplugin.elation.dto.nonVisitNoteDto.NonVisitNoteRequest;
import com.valhalla.ehrplugin.elation.service.NonVisitNoteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;


@Service
@RequiredArgsConstructor
public class NonVisitNoteServiceImpl implements NonVisitNoteService {

    private static final Logger logger = LoggerFactory.getLogger(NonVisitNoteServiceImpl.class);

    private final HttpServletRequest request;
    private final RestClient restClient;
    @Value("${elation.api.baseurl}")
    private String baseUrl;

    @Override
    public Object createNonVisitNote(NonVisitNoteRequest nonVisitNoteRequest) {
        String authorizationToken = request.getHeader("Authorization");
        logger.info("Received request to create non visit note: {}", nonVisitNoteRequest);

        try {
            ResponseEntity<Object> response = restClient.post()
                    .uri(baseUrl + "/non_visit_notes/")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", authorizationToken)
                    .body(nonVisitNoteRequest)
                    .retrieve()
                    .toEntity(Object.class);

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
