package com.valhalla.ehrplugin.elation.dto.authenticationDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthRequest {
    private int physicianId;
    private int practiceId;
}

