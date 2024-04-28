package com.valhalla.ehrplugin.elation.dto.appointmentDto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Getter
@Setter
public class Status {
    private String status;
    private String room;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date status_date;
    private String status_detail;
}
