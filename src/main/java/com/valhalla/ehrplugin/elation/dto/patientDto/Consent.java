package com.valhalla.ehrplugin.elation.dto.patientDto;

import lombok.*;

@Getter
@Setter
class Consent {
    private boolean consented;
    private String last_modified_date;
    private String application;
}
