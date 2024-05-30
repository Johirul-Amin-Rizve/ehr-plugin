package com.valhalla.ehrplugin.elation.dto.nonVisitNoteDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class NonVisitNoteRequest {

    @NotBlank(message = "Type is required")
    private String type;

    private List<NoteEntry> bullets;

    @NotNull(message = "Patient is required")
    private long patient;

    private long practice;

    @NotNull(message = "Document Date is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime document_date;

    @NotNull(message = "Chart Date is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime chart_date;

    private List<Tag> tags;

    private List<NoteDocument> note_document;

    private List<NoteItem> note_item;
}
