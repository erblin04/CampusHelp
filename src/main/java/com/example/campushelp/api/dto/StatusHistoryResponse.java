package com.example.campushelp.api.dto;

import com.example.campushelp.domain.enums.TicketStatus;

import java.time.Instant;

public class StatusHistoryResponse {

    private Long id;
    private Long ticketId;
    private TicketStatus oldStatus;
    private TicketStatus newStatus;
    private Long changedById;
    private String changedByName;
    private Instant changedAt;

    public StatusHistoryResponse(Long id, Long ticketId, TicketStatus oldStatus, TicketStatus newStatus,
                                 Long changedById, String changedByName, Instant changedAt) {
        this.id = id;
        this.ticketId = ticketId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.changedById = changedById;
        this.changedByName = changedByName;
        this.changedAt = changedAt;
    }

    public Long getId() { return id; }
    public Long getTicketId() { return ticketId; }
    public TicketStatus getOldStatus() { return oldStatus; }
    public TicketStatus getNewStatus() { return newStatus; }
    public Long getChangedById() { return changedById; }
    public String getChangedByName() { return changedByName; }
    public Instant getChangedAt() { return changedAt; }
}
