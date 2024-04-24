package com.valhalla.ehrplugin.elation.dto.patientDto;

import lombok.*;
import java.util.List;

@Getter
@Setter
public class Patient {
    private Long id;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String actual_name;
    private String gender_identity;
    private String legal_gender_marker;
    private String pronouns;
    private String sex;
    private String sexual_orientation;
    private int primary_physician;
    private int caregiver_practice;
    private String dob;
    private String ssn;
    private String race;
    private String preferred_language;
    private String ethnicity;
    private String notes;
    private boolean vip;
    private Address address;
    private List<Phone> phones;
    private List<Email> emails;
    private Guarantor guarantor;
    private List<Insurance> insurances;
    private List<Insurance> deleted_insurances;
    private List<String> tags;
    private PatientStatus patient_status;
    private Preference preference;
    private EmergencyContact emergency_contact;
    private Long primary_care_provider;
    private String primary_care_provider_npi;
    private String previous_first_name;
    private String previous_last_name;
    private PreviousName previous_name;
    private Object master_patient;
    private Employer employer;
    private List<Consent> consents;
    private Object metadata;
    private String created_date;
    private String deleted_date;
    private Long merged_into_chart;
}