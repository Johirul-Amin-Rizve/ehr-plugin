package com.valhalla.ehrplugin.elation.service.impl;

import com.valhalla.ehrplugin.elation.service.PhysicianService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PhysicianServiceImpl implements PhysicianService {

    private static final Logger logger = LoggerFactory.getLogger(PhysicianServiceImpl.class);

    private final HttpServletRequest request;
    private final RestClient restClient;

    @Value("${elation.api.baseurl}")
    private String baseUrl;

    @Override
    public Object getAllPhysicians() {
        String authorizationToken = request.getHeader("Authorization");
        logger.info("Received request to fetch physicians. Authorization token: {}", authorizationToken);

        try {
            ResponseEntity<Object> response = restClient.get()
                    .uri(baseUrl + "/physicians")
                    .accept(MediaType.APPLICATION_JSON)
                    .header("Authorization", authorizationToken)
                    .retrieve()
                    .toEntity(Object.class);

            int statusCodeValue = response.getStatusCodeValue();
            HttpStatus statusCode = (HttpStatus) response.getStatusCode();
            logger.info("Received response from API with status code: {} - {}", statusCodeValue, statusCode);

            if (statusCode.is2xxSuccessful()) {
                Object responseBody = response.getBody();
                logger.info("Retrieved physicians from the API.");
                return responseBody;
            } else {
                logger.error("Failed to retrieve physicians. Response status code: {}", statusCodeValue);
                return new Object();
            }
        } catch (RestClientException ex) {
            logger.error("Error occurred while fetching physicians: {}", ex.getMessage(), ex);
            return new Object();
        }
    }
}
