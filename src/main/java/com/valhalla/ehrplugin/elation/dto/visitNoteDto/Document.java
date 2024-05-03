package com.valhalla.ehrplugin.elation.dto.visitNoteDto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
public class Document {
    private long authoring_practice;
    private LocalDateTime chart_date;
    private LocalDateTime created_date;
    private LocalDateTime deleted_date;
    private LocalDateTime document_date;
    private int document_type;
    private long id;
    private LocalDateTime last_modified;
    private long patient;
    private LocalDateTime sign_date;
    private int signed_by;
}
