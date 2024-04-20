package com.valhalla.ehrplugin.elation.service;

import com.valhalla.ehrplugin.elation.dto.authenticationDto.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(String username, String password);
}
