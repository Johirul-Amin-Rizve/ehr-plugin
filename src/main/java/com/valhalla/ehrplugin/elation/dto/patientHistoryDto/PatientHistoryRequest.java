package com.valhalla.ehrplugin.elation.dto.patientHistoryDto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class PatientHistoryRequest {
    @NotBlank(message = "Type is required")
    private String type;

    @NotBlank(message = "Text is required")
    private String text;

    @NotBlank(message = "Patient is required")
    private String patient;
}
