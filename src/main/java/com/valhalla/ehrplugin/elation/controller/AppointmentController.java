package com.valhalla.ehrplugin.elation.controller;

import com.valhalla.ehrplugin.elation.dto.appointmentDto.Appointment;
import com.valhalla.ehrplugin.elation.dto.appointmentDto.AppointmentRequest;
import com.valhalla.ehrplugin.elation.service.AppointmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/elation")
public class AppointmentController {
    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointments")
    public ResponseEntity<Object> getAllAppointments() {
        logger.info("Received request to get appointments.");
        Object result = appointmentService.getAllAppointments();
        logger.info("Retrieved appointments successfully.");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/appointments")
    public ResponseEntity<Object> createAppointment(@Valid @RequestBody AppointmentRequest appointmentRequest) {
        logger.info("Creating a new appointment: {}", appointmentRequest);
        Object result = appointmentService.createAppointment(appointmentRequest);
        logger.info("Appointment created successfully: {}", appointmentRequest);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping("/appointments/{id}")
    public ResponseEntity<Object> updateAppointment(@PathVariable Long id, @Valid @RequestBody Appointment appointment) {
        logger.info("Updating appointment with ID {}: {}", id, appointment);

        // Check if appointment with given ID exists
        Optional<Object> existingAppointment = appointmentService.getAppointmentById(id);
        if (existingAppointment.isEmpty()) {
            logger.error("Appointment with ID {} not found", id);
            return new ResponseEntity<>("Appointment not found", HttpStatus.NOT_FOUND);
        }

        // Delegate update operation to the service class
        Object result = appointmentService.updateAppointment(id, appointment);
        logger.info("Appointment with ID {} updated successfully: {}", id, appointment);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
