package com.valhalla.ehrplugin.elation.dto.patientDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class PatientStatus {
    private String deceased_date;
    private String inactive_reason;
    private String last_status_change;
    private String notes;
    private String status;
}
