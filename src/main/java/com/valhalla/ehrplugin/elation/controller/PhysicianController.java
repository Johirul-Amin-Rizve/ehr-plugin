package com.valhalla.ehrplugin.elation.controller;

import com.valhalla.ehrplugin.elation.dto.physicianDto.Physician;
import com.valhalla.ehrplugin.elation.service.PhysicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/physicians")
public class PhysicianController {

    private final PhysicianService physicianService;

    @Autowired
    public PhysicianController(PhysicianService physicianService) {
        this.physicianService = physicianService;
    }

    @GetMapping
    public ResponseEntity<List<Physician>> getAllPhysicians() {
        List<Physician> physicians = physicianService.getAllPhysicians();
        return new ResponseEntity<>(physicians, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Physician> getPhysicianById(@PathVariable long id) {
        Physician physician = physicianService.getPhysicianById(id);
        if (physician != null) {
            return new ResponseEntity<>(physician, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
