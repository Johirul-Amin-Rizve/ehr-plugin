package com.valhalla.ehrplugin.elation.service.impl;

import com.valhalla.ehrplugin.elation.dto.authenticationDto.AuthResponse;
import com.valhalla.ehrplugin.elation.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Value("${auth.api.url}")
    private String authApiUrl;

    @Value("${auth.api.authorizationHeader}")
    private String authorizationHeader;

    @Override
    public AuthResponse authenticate(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", authorizationHeader);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("username", username);
        map.add("password", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        try {
            ResponseEntity<AuthResponse> responseEntity = new RestTemplate().postForEntity(authApiUrl, request, AuthResponse.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                LOGGER.info("Authentication successful for username: {}", username);
                return responseEntity.getBody();
            } else {
                LOGGER.warn("Authentication failed for username: {}", username);
                LOGGER.error("Error message: {}", responseEntity.getBody());
                return null;
            }
        } catch (Exception e) {
            LOGGER.error("An unexpected error occurred while authenticating for username: {}. Error message: {}", username, e.getMessage(), e);
            return null;
        }
    }
}
