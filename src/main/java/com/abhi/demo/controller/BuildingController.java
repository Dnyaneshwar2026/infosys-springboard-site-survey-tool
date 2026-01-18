package com.abhi.demo.controller;

import com.abhi.demo.entity.Building;
import com.abhi.demo.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/buildings")
@CrossOrigin(origins = "*")
public class BuildingController {

    @Autowired
    private BuildingRepository buildingRepository;

    // Get all buildings with images
    @GetMapping
    public ResponseEntity<List<Building>> getAllBuildings() {
        return ResponseEntity.ok(buildingRepository.findAll());
    }

    // Create building (image optional)
    @PostMapping
    public ResponseEntity<Building> createBuilding(@RequestBody Building building) {
        return ResponseEntity.ok(buildingRepository.save(building));
    }

    // 🆕 NEW: Serve floor plan image
    @GetMapping("/{id}/floorplan")
    public ResponseEntity<Resource> getFloorPlan(@PathVariable Long id) {
        Building building = buildingRepository.findById(id).orElse(null);
        if (building == null || building.getFloorPlanImage() == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            Path filePath = Paths.get("uploads/floorplans/" + building.getFloorPlanImage());
            Resource resource = new UrlResource(filePath.toUri());
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
