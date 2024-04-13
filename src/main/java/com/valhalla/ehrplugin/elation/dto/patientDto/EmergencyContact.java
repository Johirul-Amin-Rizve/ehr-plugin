package com.valhalla.ehrplugin.elation.dto.patientDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class EmergencyContact {
    private String first_name;
    private String last_name;
    private String relationship;
    private String phone;
    private String address_line1;
    private String address_line2;
    private String city;
    private String state;
    private String zip;
}
