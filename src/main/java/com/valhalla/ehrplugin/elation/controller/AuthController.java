package com.valhalla.ehrplugin.elation.controller;

import com.valhalla.ehrplugin.elation.dto.authenticationDto.AuthRequest;
import com.valhalla.ehrplugin.elation.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elation")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/authenticate")
    public Object authenticate(@RequestBody AuthRequest request) {
        LOGGER.info("Received authentication request: {}", request);
        Object response = authService.authenticate(request);
        if (response != null) {
            LOGGER.info("Authentication successful for request: {}", request);
        } else {
            LOGGER.warn("Authentication failed for request: {}", request);
        }
        return response;
    }
}
