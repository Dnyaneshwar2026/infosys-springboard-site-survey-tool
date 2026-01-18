package com.abhi.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "*")
public class FileController {

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFloorPlan(@RequestParam("file") MultipartFile file) {
        try {
            String folder = "uploads/floorplans/";
            String fileName = "floorplan_" + System.currentTimeMillis() +
                    getFileExtension(file.getOriginalFilename());

            Path path = Paths.get(folder + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes(), StandardOpenOption.CREATE_NEW);

            return ResponseEntity.ok("✅ Saved: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("❌ " + e.getMessage());
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName == null) return ".jpg";
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
