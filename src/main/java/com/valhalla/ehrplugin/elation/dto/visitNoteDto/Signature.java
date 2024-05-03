package com.valhalla.ehrplugin.elation.dto.visitNoteDto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
public class Signature {
    private int user;
    private String userName;
    private LocalDateTime signedDate;
    private String role;
    private String comments;
}
