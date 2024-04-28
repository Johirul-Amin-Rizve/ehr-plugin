package com.valhalla.ehrplugin.elation.dto.appointmentDto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Getter
@Setter
public class Appointment {
    private long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date scheduled_date;
    private int duration;
    private String time_slot_type;
    private String time_slot_status;
    private String reason;
    private String description;
    private Status status;
    private ServiceLocation service_location;
    private String telehealth_details;
    private long patient;
    private long physician;
    private long practice;
    private String recurring_event_schedule;
    private BillingDetails billing_details;
    private Payment payment;
    private String metadata;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date created_date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date last_modified_date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date deleted_date;
    private String mode;
    private String instructions;
}
