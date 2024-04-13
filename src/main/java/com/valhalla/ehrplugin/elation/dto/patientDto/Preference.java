package com.valhalla.ehrplugin.elation.dto.patientDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class Preference {
    private String preferred_pharmacy_1;
    private String preferred_pharmacy_2;
}
