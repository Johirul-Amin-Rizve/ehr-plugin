package com.valhalla.ehrplugin.elation.service;

import com.valhalla.ehrplugin.elation.dto.reportDto.ReportRequest;

public interface ReportService {
    Object createReport(ReportRequest reportRequest);
}
