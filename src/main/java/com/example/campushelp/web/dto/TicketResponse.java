package com.example.campushelp.web.dto;

import com.example.campushelp.domain.enums.TicketPriority;
import com.example.campushelp.domain.enums.TicketStatus;

import java.time.Instant;

public class TicketResponse {

    private Long id;
    private String title;
    private String description;
    private String category;
    private TicketPriority priority;
    private TicketStatus status;

    private Long requesterId;
    private String requesterName;

    private Long assignedToId;
    private String assignedToName;

    private Instant createdAt;
    private Instant updatedAt;

    public TicketResponse(
            Long id,
            String title,
            String description,
            String category,
            TicketPriority priority,
            TicketStatus status,
            Long requesterId,
            String requesterName,
            Long assignedToId,
            String assignedToName,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.status = status;
        this.requesterId = requesterId;
        this.requesterName = requesterName;
        this.assignedToId = assignedToId;
        this.assignedToName = assignedToName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public TicketPriority getPriority() { return priority; }
    public TicketStatus getStatus() { return status; }
    public Long getRequesterId() { return requesterId; }
    public String getRequesterName() { return requesterName; }
    public Long getAssignedToId() { return assignedToId; }
    public String getAssignedToName() { return assignedToName; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}
