package com.example.campushelp.web;

import com.example.campushelp.service.TicketService;
import com.example.campushelp.web.dto.CreateTicketRequest;
import com.example.campushelp.web.dto.TicketResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponse create(@Valid @RequestBody CreateTicketRequest req) {
        return ticketService.create(req);
    }

    @GetMapping
    public List<TicketResponse> list() {
        return ticketService.getAll();
    }

    @GetMapping("/{id}")
    public TicketResponse getById(@PathVariable Long id) {
        return ticketService.getById(id);
    }
}
