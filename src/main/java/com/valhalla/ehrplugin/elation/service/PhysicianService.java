package com.valhalla.ehrplugin.elation.service;

import com.valhalla.ehrplugin.elation.dto.physicianDto.Physician;

import java.util.List;

public interface PhysicianService {
    List<Physician> getAllPhysicians();
    Physician getPhysicianById(long id);
}

