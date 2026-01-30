package com.example.campushelp.api.dto;

import com.example.campushelp.domain.enums.TicketStatus;
import jakarta.validation.constraints.NotNull;

public class ChangeStatusRequest {

    @NotNull
    private TicketStatus newStatus;

    @NotNull
    private Long changedById;

    public TicketStatus getNewStatus() { return newStatus; }
    public void setNewStatus(TicketStatus newStatus) { this.newStatus = newStatus; }

    public Long getChangedById() { return changedById; }
    public void setChangedById(Long changedById) { this.changedById = changedById; }
}
