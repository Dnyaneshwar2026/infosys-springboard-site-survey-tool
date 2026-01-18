package com.abhi.demo.dto;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadRequest {
    private String fileName;
    private String fileType;
    private MultipartFile file;

    // Constructors
    public FileUploadRequest() {}

    // Getters/Setters
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public MultipartFile getFile() { return file; }
    public void setFile(MultipartFile file) { this.file = file; }
}
