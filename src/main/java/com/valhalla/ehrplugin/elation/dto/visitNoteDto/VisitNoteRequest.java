package com.valhalla.ehrplugin.elation.dto.visitNoteDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class VisitNoteRequest {


    @NotNull(message = "Bullets are required")
    private List<Bullet> bullets;

    @NotNull(message = "Chart Date is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime chart_date;

    @NotNull(message = "Document Date is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime document_date;

    @NotNull(message = "Patient is required")
    private long patient;

    @NotBlank(message = "Template is required")
    private String template;

    @NotNull(message = "Physician is required")
    private long physician;

    private String type;

    private boolean confidential;

    private long signed_by;

    private LocalDateTime signed_date;

    private List<Signature> signatures;
}
