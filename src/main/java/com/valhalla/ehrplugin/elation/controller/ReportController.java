package com.valhalla.ehrplugin.elation.controller;

import com.valhalla.ehrplugin.elation.dto.reportDto.ReportRequest;
import com.valhalla.ehrplugin.elation.service.ReportService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/elation")
public class ReportController {
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/reports")
    public ResponseEntity<Object> createReport(@Valid @RequestBody ReportRequest reportRequest) {
        logger.info("Creating a report: {}", reportRequest);
        Object result = reportService.createReport(reportRequest);
        logger.info("Report created successfully: {}", reportRequest);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
