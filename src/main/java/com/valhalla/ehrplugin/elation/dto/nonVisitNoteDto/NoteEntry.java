package com.valhalla.ehrplugin.elation.dto.nonVisitNoteDto;

import java.time.LocalDateTime;

public class NoteEntry {
    private long id;
    private long author;
    private String text;
    private int version;
    private LocalDateTime updated_date;
}
