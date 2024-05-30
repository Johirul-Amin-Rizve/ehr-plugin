package com.valhalla.ehrplugin.elation.dto.nonVisitNoteDto;

import java.time.LocalDateTime;

public class Item {
    private long id;
    private LocalDateTime created_date;
    private LocalDateTime deleted_date;
    private long patient;
    private String type;
    private boolean is_confidential;
}
