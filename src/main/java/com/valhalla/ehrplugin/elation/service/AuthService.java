package com.valhalla.ehrplugin.elation.service;

import com.valhalla.ehrplugin.elation.dto.authenticationDto.AuthRequest;

public interface AuthService {
    Object authenticate(AuthRequest request);
}
