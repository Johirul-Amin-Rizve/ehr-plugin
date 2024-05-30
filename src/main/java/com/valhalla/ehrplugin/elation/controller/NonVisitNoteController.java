package com.valhalla.ehrplugin.elation.controller;

import com.valhalla.ehrplugin.elation.dto.nonVisitNoteDto.NonVisitNoteRequest;
import com.valhalla.ehrplugin.elation.service.NonVisitNoteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/elation")
public class NonVisitNoteController {
    private static final Logger logger = LoggerFactory.getLogger(NonVisitNoteController.class);
    private final NonVisitNoteService nonVisitNoteService;

    public NonVisitNoteController(NonVisitNoteService nonVisitNoteService) {
        this.nonVisitNoteService = nonVisitNoteService;
    }

    @PostMapping("/non_visit_notes")
    public ResponseEntity<Object> createNonVisitNote(@Valid @RequestBody NonVisitNoteRequest nonVisitNoteRequest) {
        logger.info("Creating a new visit note: {}", nonVisitNoteRequest);
        Object result = nonVisitNoteService.createNonVisitNote(nonVisitNoteRequest);
        logger.info("Visit note created successfully: {}", nonVisitNoteRequest);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
