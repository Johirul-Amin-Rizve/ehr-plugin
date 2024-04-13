package com.valhalla.ehrplugin.elation.dto.patientDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class Guarantor {
    private Long id;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String relationship;
    private String first_name;
    private String last_name;
    private String middle_name;
}
