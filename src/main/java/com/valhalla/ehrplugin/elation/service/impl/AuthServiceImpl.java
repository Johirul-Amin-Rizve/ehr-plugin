package com.valhalla.ehrplugin.elation.service.impl;

import com.valhalla.ehrplugin.elation.core.Authentication;
import com.valhalla.ehrplugin.elation.core.AuthenticationEnum;
import com.valhalla.ehrplugin.elation.dto.authenticationDto.AuthRequest;
import com.valhalla.ehrplugin.elation.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final RestClient restClient;

    @Value("${elation.api.baseurl}")
    private String baseUrl;

    @Override
    public Object authenticate(AuthRequest authRequest) {
        Authentication authentication = AuthenticationEnum.getAuthenticationByPhysicianAndPracticeId(authRequest.getPhysicianId(), authRequest.getPracticeId());

        if (authentication == null) {
            throw new IllegalStateException("Authentication details not found for physician: " + authRequest.getPhysicianId() + " and practice: " + authRequest.getPracticeId());
        }

        MultiValueMap<String, String> credential = new LinkedMultiValueMap<>();
        credential.add("grant_type", "password");
        credential.add("username", authentication.getUsername());
        credential.add("password", authentication.getPassword());

        try {
            ResponseEntity<Object> responseEntity = restClient.post()
                    .uri(baseUrl + "/oauth2/token/")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .header("Authorization", authentication.getBasicToken())
                    .body(credential)
                    .retrieve()
                    .toEntity(Object.class);


            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                logger.info("Authentication successful for username: {}", authentication.getUsername());
                return responseEntity.getBody();
            } else {
                logger.warn("Authentication failed for username: {}", authentication.getUsername());
                logger.error("Error message: {}", responseEntity.getBody());
                return null;
            }
        } catch (Exception e) {
            logger.error("An unexpected error occurred while authenticating for username: {}. Error message: {}", authentication.getUsername(), e.getMessage(), e);
            return null;
        }
    }
}
