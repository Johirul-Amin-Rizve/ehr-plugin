package com.valhalla.ehrplugin.elation.service;

import com.valhalla.ehrplugin.elation.dto.appointmentDto.Appointment;
import com.valhalla.ehrplugin.elation.dto.appointmentDto.AppointmentRequest;

import java.util.Optional;

public interface AppointmentService {
    Object getAllAppointments();

    Optional<Object> getAppointmentById(Long id);

    Object createAppointment(AppointmentRequest appointmentRequest);

    Object updateAppointment(Long id, Appointment appointment);
}

