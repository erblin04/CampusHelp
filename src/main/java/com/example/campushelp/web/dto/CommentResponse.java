package com.example.campushelp.web.dto;

import java.time.Instant;

public class CommentResponse {
    private Long id;
    private Long ticketId;
    private Long authorId;
    private String authorName;
    private String body;
    private boolean internal;
    private Instant createdAt;

    public CommentResponse(Long id, Long ticketId, Long authorId, String authorName,
                           String body, boolean internal, Instant createdAt) {
        this.id = id;
        this.ticketId = ticketId;
        this.authorId = authorId;
        this.authorName = authorName;
        this.body = body;
        this.internal = internal;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public Long getTicketId() { return ticketId; }
    public Long getAuthorId() { return authorId; }
    public String getAuthorName() { return authorName; }
    public String getBody() { return body; }
    public boolean isInternal() { return internal; }
    public Instant getCreatedAt() { return createdAt; }
}
