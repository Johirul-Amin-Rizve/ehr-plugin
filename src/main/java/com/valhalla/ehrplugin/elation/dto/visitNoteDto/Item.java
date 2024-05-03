package com.valhalla.ehrplugin.elation.dto.visitNoteDto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
public class Item {
    private long id;
    private LocalDateTime created_date;
    private LocalDateTime deleted_date;
    private long patient;
    private String type;
    private boolean is_confidential;
    private String itemType;
}
