package com.example.campushelp.web.dto;

import java.time.Instant;

public class AttachmentResponse {
    private Long id;
    private Long ticketId;
    private String fileName;
    private String filePath;
    private Instant uploadedAt;

    public AttachmentResponse(Long id, Long ticketId, String fileName, String filePath, Instant uploadedAt) {
        this.id = id;
        this.ticketId = ticketId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.uploadedAt = uploadedAt;
    }

    public Long getId() { return id; }
    public Long getTicketId() { return ticketId; }
    public String getFileName() { return fileName; }
    public String getFilePath() { return filePath; }
    public Instant getUploadedAt() { return uploadedAt; }
}
