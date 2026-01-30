package com.example.campushelp.api.dto;

import jakarta.validation.constraints.NotBlank;

public class AddAttachmentRequest {
    @NotBlank private String fileName;
    @NotBlank private String filePath;

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
}
