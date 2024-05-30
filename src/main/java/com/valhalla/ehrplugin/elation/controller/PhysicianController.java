package com.valhalla.ehrplugin.elation.controller;

import com.valhalla.ehrplugin.elation.service.PhysicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("elation/physicians")
public class PhysicianController {

    private static final Logger logger = Logger.getLogger(PhysicianController.class.getName());

    private final PhysicianService physicianService;

    @Autowired
    public PhysicianController(PhysicianService physicianService) {
        this.physicianService = physicianService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllPhysicians() {
        logger.info("Received request to get all physicians.");
        Object result = physicianService.getAllPhysicians();
        logger.info("Retrieved all physicians successfully.");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
