package com.valhalla.ehrplugin.elation.controller;

import com.valhalla.ehrplugin.elation.service.PhysicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("elation/physicians")
public class PhysicianController {

    private final PhysicianService physicianService;

    @Autowired
    public PhysicianController(PhysicianService physicianService) {
        this.physicianService = physicianService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllPhysicians() {
        Object result = physicianService.getAllPhysicians();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
