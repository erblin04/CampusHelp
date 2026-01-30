package com.example.campushelp.api;

import com.example.campushelp.api.dto.UpdateTicketRequest;
import com.example.campushelp.service.TicketService;
import com.example.campushelp.api.dto.CreateTicketRequest;
import com.example.campushelp.api.dto.TicketResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping
//    public List<TicketResponse> list() {
//        return ticketService.getAll();
//    }

    @GetMapping
    public org.springframework.data.domain.Page<TicketResponse> list(
            @RequestParam(required = false) com.example.campushelp.domain.enums.TicketStatus status,
            @RequestParam(required = false) com.example.campushelp.domain.enums.TicketPriority priority,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        return ticketService.search(status, priority, category, q, page, size, sortBy, direction);
    }



    @GetMapping("/{id}")
    public TicketResponse getById(@PathVariable Long id) {
        return ticketService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        ticketService.delete(id);
    }

    @PutMapping("/{id}")
    public TicketResponse update(@PathVariable Long id,
                                 @RequestBody UpdateTicketRequest req) {
        return ticketService.update(id, req);
    }
}
