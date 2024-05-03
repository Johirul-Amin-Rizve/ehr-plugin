package com.valhalla.ehrplugin.elation.controller;

import com.valhalla.ehrplugin.elation.dto.visitNoteDto.VisitNoteRequest;
import com.valhalla.ehrplugin.elation.service.VisitNoteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/elation")
public class VisitNoteController {
    private static final Logger logger = LoggerFactory.getLogger(VisitNoteController.class);
    private final VisitNoteService visitNoteService;

    public VisitNoteController(VisitNoteService visitNoteService) {
        this.visitNoteService = visitNoteService;
    }

    @GetMapping("/visit_notes")
    public ResponseEntity<Object> getAllVisitNotes() {
        logger.info("Received request to get visit note.");
        Object result = visitNoteService.getAllVisitNotes();
        logger.info("Retrieved visit note successfully.");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/visit_notes")
    public ResponseEntity<Object> createVisitNote(@Valid @RequestBody VisitNoteRequest visitNoteRequest) {
        logger.info("Creating a new visit note: {}", visitNoteRequest);
        Object result = visitNoteService.createVisitNote(visitNoteRequest);
        logger.info("Visit note created successfully: {}", visitNoteRequest);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @DeleteMapping("/visit_notes/{id}")
    public ResponseEntity<Object> deleteVisitNote(@PathVariable Long id) {
        logger.info("Deleting visit note with ID: {}", id);
        Object result = visitNoteService.deleteVisitNote(id);
        if (result != null) {
            logger.info("Visit note deleted successfully with ID: {}", id);
            return ResponseEntity.ok(result);
        } else {
            logger.warn("Visit note not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}
