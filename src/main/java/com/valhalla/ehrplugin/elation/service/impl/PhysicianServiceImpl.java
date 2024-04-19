package com.valhalla.ehrplugin.elation.service.impl;

import com.valhalla.ehrplugin.elation.dto.physicianDto.Physician;
import com.valhalla.ehrplugin.elation.service.PhysicianService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhysicianServiceImpl implements PhysicianService {

    // Simulated database
    private static List<Physician> physicians = new ArrayList<>();

    // Sample data initialization (you can replace with your data retrieval logic)
    static {
        physicians.add(new Physician(64811630594L, "Gary", "Leung", "1234567890", "G3455", "CA", "MD",
                "Family Medicine", "", 342, 65540L, true, null));
        // Add more sample data if needed
    }

    @Override
    public List<Physician> getAllPhysicians() {
        return physicians;
    }

    @Override
    public Physician getPhysicianById(long id) {
        for (Physician physician : physicians) {
            if (physician.getId() == id) {
                return physician;
            }
        }
        return null; // Return null if physician with given ID is not found
    }
}

