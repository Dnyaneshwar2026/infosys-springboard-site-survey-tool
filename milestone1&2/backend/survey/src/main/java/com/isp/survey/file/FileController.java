package com.isp.survey.file;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        String filename = file.getOriginalFilename();

        if (filename == null ||
            !(filename.endsWith(".png")
            || filename.endsWith(".jpg")
            || filename.endsWith(".jpeg")
            || filename.endsWith(".pdf"))) {

            throw new RuntimeException("Only PNG, JPG, JPEG, PDF files are allowed");
        }

        // For Milestone-2: we only return filename (no storage yet)
        return filename;
    }
}
