package com.valhalla.ehrplugin.elation.dto.appointmentDto;

import lombok.*;

@Getter
@Setter
public class ServiceLocation {
    private long id;
    private String name;
    private int place_of_service;
    private String address_line1;
    private String address_line2;
    private String city;
    private String state;
    private String zip;
    private String phone;
}
