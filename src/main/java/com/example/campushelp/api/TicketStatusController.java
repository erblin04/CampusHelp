package com.example.campushelp.api;

import com.example.campushelp.service.TicketStatusService;
import com.example.campushelp.api.dto.ChangeStatusRequest;
import com.example.campushelp.api.dto.StatusHistoryResponse;
import com.example.campushelp.api.dto.TicketResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketStatusController {

    private final TicketStatusService ticketStatusService;

    public TicketStatusController(TicketStatusService ticketStatusService) {
        this.ticketStatusService = ticketStatusService;
    }

    @PatchMapping("/{id}/status")
    public TicketResponse changeStatus(@PathVariable Long id, @Valid @RequestBody ChangeStatusRequest req) {
        return ticketStatusService.changeStatus(id, req);
    }

    @GetMapping("/{id}/history")
    public List<StatusHistoryResponse> history(@PathVariable Long id) {
        return ticketStatusService.history(id);
    }
}
