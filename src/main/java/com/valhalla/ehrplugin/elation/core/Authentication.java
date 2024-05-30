package com.valhalla.ehrplugin.elation.core;

import lombok.Getter;

@Getter
public class Authentication {
    private final int physicianId;
    private final int practiceId;
    private final String username;
    private final String password;
    private final String basicToken;

    public Authentication(int physicianId, int practiceId, String username, String password, String basicToken) {
        this.physicianId = physicianId;
        this.practiceId = practiceId;
        this.username = username;
        this.password = password;
        this.basicToken = basicToken;
    }
}
