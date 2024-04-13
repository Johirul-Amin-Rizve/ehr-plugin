package com.valhalla.ehrplugin.elation.dto.patientDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class PreviousName {
    private String first_name;
    private String middle_name;
    private String last_name;
    private String suffix;

}
