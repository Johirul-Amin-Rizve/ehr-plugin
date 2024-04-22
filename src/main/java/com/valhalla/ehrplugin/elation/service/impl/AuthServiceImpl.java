package com.valhalla.ehrplugin.elation.service.impl;

import com.valhalla.ehrplugin.elation.core.Authentication;
import com.valhalla.ehrplugin.elation.core.AuthenticationEnum;
import com.valhalla.ehrplugin.elation.dto.authenticationDto.AuthRequest;
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

    @Value("${elation.api.baseurl}")
    private String baseUrl;

    @Override
    public Object authenticate(AuthRequest authRequest) {
        Authentication authentication = AuthenticationEnum.getAuthenticationByPhysicianAndPracticeId(authRequest.getPhysicianId(), authRequest.getPracticeId());

        if (authentication == null) {
            throw new IllegalStateException("Authentication details not found for physician: " + authRequest.getPhysicianId() + " and practice: " + authRequest.getPracticeId());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", authentication.getBasicToken());

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("username", authentication.getUsername());
        map.add("password", authentication.getPassword());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        try {
            ResponseEntity<Object> responseEntity = new RestTemplate().postForEntity(baseUrl+ "/oauth2/token/", request, Object.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                LOGGER.info("Authentication successful for username: {}", authentication.getUsername());
                return responseEntity.getBody();
            } else {
                LOGGER.warn("Authentication failed for username: {}", authentication.getUsername());
                LOGGER.error("Error message: {}", responseEntity.getBody());
                return null;
            }
        } catch (Exception e) {
            LOGGER.error("An unexpected error occurred while authenticating for username: {}. Error message: {}", authentication.getUsername(), e.getMessage(), e);
            return null;
        }
    }
}
