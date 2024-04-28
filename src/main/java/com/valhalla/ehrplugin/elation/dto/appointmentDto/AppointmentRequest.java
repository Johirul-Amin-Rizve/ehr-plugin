package com.valhalla.ehrplugin.elation.dto.appointmentDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AppointmentRequest {

    @NotNull(message = "Scheduled Date is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date scheduled_date;

    private int duration;

    @NotBlank(message = "Reason is required")
    private String reason;

    private String description;

    @NotNull(message = "Patient is required")
    private long patient;

    @NotNull(message = "Physician is required")
    private long physician;

    @NotNull(message = "Practice is required")
    private long practice;

    private int service_location;
}
