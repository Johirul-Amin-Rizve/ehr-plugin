package com.valhalla.ehrplugin.elation.dto.patientDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class Phone {
    private String phone;
    private String phone_type;
    private String created_date;
    private String deleted_date;
}
