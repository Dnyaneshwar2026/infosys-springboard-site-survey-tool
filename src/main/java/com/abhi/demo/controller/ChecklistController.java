package com.abhi.demo.controller;

import com.abhi.demo.entity.Checklist;
import com.abhi.demo.repository.ChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/checklists")
@CrossOrigin(origins = "*")
public class ChecklistController {

    @Autowired
    private ChecklistRepository checklistRepository;

    @PostMapping
    public ResponseEntity<Checklist> create(@RequestBody Checklist checklist) {
        return ResponseEntity.ok(checklistRepository.save(checklist));
    }

    @GetMapping("/building/{buildingId}")
    public ResponseEntity<List<Checklist>> getByBuilding(@PathVariable String buildingId) {
        return ResponseEntity.ok(checklistRepository.findByBuildingId(buildingId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Checklist> getById(@PathVariable Long id) {
        return checklistRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/sync")
    public ResponseEntity<String> syncOfflineData(@RequestBody List<Checklist> checklists) {
        checklistRepository.saveAll(checklists);
        return ResponseEntity.ok("Synced " + checklists.size() + " checklists");
    }
}
