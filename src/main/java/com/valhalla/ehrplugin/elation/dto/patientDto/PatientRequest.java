package com.valhalla.ehrplugin.elation.dto.patientDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PatientRequest {
    @NotBlank(message = "First name is required")
    private String first_name;

    @NotBlank(message = "Last name is required")
    private String last_name;

    @NotNull(message = "Date of birth is required")
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private String dob;

    @NotBlank(message = "Sex is required")
    @Pattern(regexp = "^(Male|Female|Unknown)$", message = "Invalid sex")
    private String sex;

    @NotNull(message = "Primary physician is required")
    private Long primary_physician;

    @NotNull(message = "Caregiver practice is required")
    private Long caregiver_practice;
    private Address address;
    private List<Phone> phones;
    private List<Email> emails;
    private List<Insurance> insurances;
}
