package com.valhalla.ehrplugin.elation.dto.visitNoteDto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

@Getter
@Setter
public class Bullet {
    private String category;
    private String text;
    private int version;
    private int sequence;
    private int author;
    private LocalDateTime updated_date;
    private Long replaced_by_edit;
    private Long replaced_by;
    private Long edit;
    private LocalDateTime deleted_date;
    private NoteDocument note_document;
    private NoteItem note_item;
    private Long handout;
    private List<Bullet> children;
}
