package com.valhalla.ehrplugin.elation.dto.authenticationDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthResponse {
    private String token_type;
    private int expires_in;
    private String access_token;
    private String scope;
    private String refresh_token;
}

