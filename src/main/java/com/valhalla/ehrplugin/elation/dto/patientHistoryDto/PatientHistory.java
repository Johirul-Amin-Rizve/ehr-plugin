package com.valhalla.ehrplugin.elation.dto.patientHistoryDto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientHistory {
    private Long id;
    private String type;
    private int rank;
    private String text;
    private Long patient;
    private Date createdDate;
    private Date deletedDate;
}
