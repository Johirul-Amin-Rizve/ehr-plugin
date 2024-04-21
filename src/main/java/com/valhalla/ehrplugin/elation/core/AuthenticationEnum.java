package com.valhalla.ehrplugin.elation.core;

import java.util.ArrayList;
import java.util.List;

public enum AuthenticationEnum {
    OBJECT_1(1, 1, "2132-346@api.elationemr.com", "bb513be17f386ffcc2e8e90790d27032", "Basic dGlsYTJGeU1vMnFTbGR3RkNOYnNxQnNYSmZVODFTOGdWQmRSM1NjRjo4VVlmMWdaOEtQeURoRjZieFlCVWRnd2pPQVRwZnVzSmt0b0RsOE11T3BJMGVwY0ZSVjlBNm1kT0ZNY2MxeThWS3VBNGRESUExbGhYc3A3dVVuZlpaN1dXYjA4R2VDWlJnMWFydFdqV0c4SVdDaFB3ZmIwT0lYRzB3Z2tvMUpsNw=="),
    OBJECT_2(2, 2, "user2", "pass2", "token2"),
    OBJECT_3(3, 3, "user3", "pass3", "token3");

    private final int physicianId;
    private final int practiceId;
    private final String username;
    private final String password;
    private final String basicToken;

    AuthenticationEnum(int physicianId, int practiceId, String username, String password, String basicToken) {
        this.physicianId = physicianId;
        this.practiceId = practiceId;
        this.username = username;
        this.password = password;
        this.basicToken = basicToken;
    }

    // Method to get a list of all objects in the enum
    public static List<Authentication> getAllObjects() {
        List<Authentication> authenticationList = new ArrayList<>();
        for (AuthenticationEnum authenticationEnum : AuthenticationEnum.values()) {
            authenticationList.add(new Authentication(authenticationEnum.physicianId, authenticationEnum.practiceId, authenticationEnum.username, authenticationEnum.password, authenticationEnum.basicToken));
        }
        return authenticationList;
    }

    // Method to get authentication object by physicianId
    public static Authentication getAuthenticationByPhysicianId(int physicianId) {
        for (AuthenticationEnum authenticationEnum : AuthenticationEnum.values()) {
            if (authenticationEnum.physicianId == physicianId) {
                return new Authentication(authenticationEnum.physicianId, authenticationEnum.practiceId, authenticationEnum.username, authenticationEnum.password, authenticationEnum.basicToken);
            }
        }
        return null; // Return null if no matching physicianId is found
    }

    // Method to get authentication object by practiceId
    public static Authentication getAuthenticationByPracticeId(int practiceId) {
        for (AuthenticationEnum authenticationEnum : AuthenticationEnum.values()) {
            if (authenticationEnum.practiceId == practiceId) {
                return new Authentication(authenticationEnum.physicianId, authenticationEnum.practiceId, authenticationEnum.username, authenticationEnum.password, authenticationEnum.basicToken);
            }
        }
        return null; // Return null if no matching practiceId is found
    }

    // Method to get authentication object by both physicianId and practiceId
    public static Authentication getAuthenticationByPhysicianAndPracticeId(int physicianId, int practiceId) {
        for (AuthenticationEnum authenticationEnum : AuthenticationEnum.values()) {
            if (authenticationEnum.physicianId == physicianId && authenticationEnum.practiceId == practiceId) {
                return new Authentication(authenticationEnum.physicianId, authenticationEnum.practiceId, authenticationEnum.username, authenticationEnum.password, authenticationEnum.basicToken);
            }
        }
        return null; // Return null if no matching physicianId and practiceId combination is found
    }
}
