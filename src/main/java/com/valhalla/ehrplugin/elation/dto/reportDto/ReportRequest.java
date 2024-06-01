package com.valhalla.ehrplugin.elation.dto.reportDto;

import java.util.List;

import lombok.*;

@Getter
@Setter
public class ReportRequest {
    private String report_type;
    private long patient;
    private long physician;
    private long practice;
    private String reported_date;
    private String chart_date;
    private String document_date;
    private String custom_title;
    private List<File> files;
}
