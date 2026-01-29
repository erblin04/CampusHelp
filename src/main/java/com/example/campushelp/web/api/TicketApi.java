package com.example.campushelp.web.api;

import com.example.campushelp.domain.enums.TicketPriority;
import com.example.campushelp.domain.enums.TicketStatus;
import com.example.campushelp.web.dto.CreateTicketRequest;
import com.example.campushelp.web.dto.TicketResponse;
import com.example.campushelp.web.dto.UpdateTicketRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/tickets")
public interface TicketApi {

    // CREATE
    @PostMapping
    TicketResponse create(@Valid @RequestBody CreateTicketRequest req);

    // READ (list + filtering + paging + sorting)
    @GetMapping
    Page<TicketResponse> list(
            @RequestParam(required = false) TicketStatus status,
            @RequestParam(required = false) TicketPriority priority,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    );

    // READ
    @GetMapping("/{id}")
    TicketResponse getById(@PathVariable Long id);

    // UPDATE
    @PutMapping("/{id}")
    TicketResponse update(@PathVariable Long id, @Valid @RequestBody UpdateTicketRequest req);

    // UPDATE
    @PatchMapping("/{id}/assign")
    TicketResponse assign(@PathVariable Long id, @RequestParam Long assignedToId);

    // DELETE
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}
