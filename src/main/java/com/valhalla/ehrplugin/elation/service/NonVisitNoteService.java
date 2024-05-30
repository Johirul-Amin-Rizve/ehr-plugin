package com.valhalla.ehrplugin.elation.service;

import com.valhalla.ehrplugin.elation.dto.nonVisitNoteDto.NonVisitNoteRequest;

public interface NonVisitNoteService {
    Object createNonVisitNote(NonVisitNoteRequest nonVisitNoteRequest);
}
