package com.valhalla.ehrplugin.elation.service;

import com.valhalla.ehrplugin.elation.dto.visitNoteDto.VisitNoteRequest;

public interface VisitNoteService {
    Object getAllVisitNotes();

    Object createVisitNote(VisitNoteRequest visitNoteRequest);

    Object deleteVisitNote(Long id);
}
