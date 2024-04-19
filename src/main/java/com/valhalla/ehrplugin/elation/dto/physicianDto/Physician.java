package com.valhalla.ehrplugin.elation.dto.physicianDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Physician {
    private Long id;
    private String first_name;
    private String last_name;
    private String npi;
    private String license;
    private String license_state;
    private String credentials;
    private String specialty;
    private String email;
    private int user_id;
    private Long practice;
    private boolean is_active;
    private Object metadata;
}