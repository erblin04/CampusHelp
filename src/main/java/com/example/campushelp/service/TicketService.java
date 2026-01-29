package com.example.campushelp.service;

import com.example.campushelp.domain.Ticket;
import com.example.campushelp.domain.User;
import com.example.campushelp.repositoy.TicketRepository;
import com.example.campushelp.web.dto.CreateTicketRequest;
import com.example.campushelp.web.dto.TicketResponse;
import com.example.campushelp.web.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserService userService;

    public TicketService(TicketRepository ticketRepository, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
    }

    public TicketResponse create(CreateTicketRequest req) {
        User requester = userService.getEntity(req.getRequesterId());

        Ticket ticket = new Ticket();
        ticket.setTitle(req.getTitle());
        ticket.setDescription(req.getDescription());
        ticket.setCategory(req.getCategory());
        ticket.setPriority(req.getPriority());
        ticket.setRequester(requester);

        return toResponse(ticketRepository.save(ticket));
    }

    public TicketResponse getById(Long id) {
        return ticketRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new NotFoundException("Ticket not found"));
    }

    public List<TicketResponse> getAll() {
        return ticketRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private TicketResponse toResponse(Ticket t) {
        return new TicketResponse(
                t.getId(),
                t.getTitle(),
                t.getDescription(),
                t.getCategory(),
                t.getPriority(),
                t.getStatus(),
                t.getRequester().getId(),
                t.getRequester().getFullName(),
                t.getAssignedTo() == null ? null : t.getAssignedTo().getId(),
                t.getAssignedTo() == null ? null : t.getAssignedTo().getFullName(),
                t.getCreatedAt(),
                t.getUpdatedAt()
        );
    }
}
