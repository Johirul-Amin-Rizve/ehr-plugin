package com.valhalla.ehrplugin.elation.dto.patientDto;

import lombok.*;

@Getter
@Setter
class Insurance {
    private Long id;
    private Long insurance_company;
    private Long insurance_plan;
    private String rank;
    private String carrier;
    private String member_id;
    private String group_id;
    private String plan;
    private String phone;
    private String extension;
    private String address;
    private String suite;
    private String city;
    private String state;
    private String zip;
    private Double copay;
    private Double deductible;
    private String payment_program;
    private String insured_person_first_name;
    private String insured_person_last_name;
    private String insured_person_address;
    private String insured_person_city;
    private String insured_person_state;
    private String insured_person_zip;
    private String insured_person_id;
    private String insured_person_dob;
    private String insured_person_gender;
    private String insured_person_ssn;
    private String relationship_to_insured;
    private String created_date;
    private String deleted_date;
    private String start_date;
    private String end_date;
}
