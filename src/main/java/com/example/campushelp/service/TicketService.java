package com.example.campushelp.service;

import com.example.campushelp.domain.Ticket;
import com.example.campushelp.domain.User;
import com.example.campushelp.repositoy.TicketRepository;
import com.example.campushelp.web.dto.CreateTicketRequest;
import com.example.campushelp.web.dto.TicketResponse;
import com.example.campushelp.web.exception.NotFoundException;
import org.springframework.stereotype.Service;
import com.example.campushelp.domain.enums.TicketPriority;
import com.example.campushelp.domain.enums.TicketStatus;
import com.example.campushelp.repositoy.TicketSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;



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

    public Page<TicketResponse> search(
            TicketStatus status,
            TicketPriority priority,
            String category,
            String q,
            int page,
            int size,
            String sortBy,
            String direction
    ) {
        // allow only safe sort fields
        String safeSortBy = switch (sortBy == null ? "" : sortBy) {
            case "createdAt" -> "createdAt";
            case "updatedAt" -> "updatedAt";
            case "priority" -> "priority";
            case "status" -> "status";
            default -> "createdAt";
        };

        Sort sort = "asc".equalsIgnoreCase(direction)
                ? Sort.by(safeSortBy).ascending()
                : Sort.by(safeSortBy).descending();

        PageRequest pageable = PageRequest.of(page, size, sort);

        Specification<com.example.campushelp.domain.Ticket> spec = Specification.where(TicketSpecifications.hasStatus(status))
                .and(TicketSpecifications.hasPriority(priority))
                .and(TicketSpecifications.hasCategory(category))
                .and(TicketSpecifications.titleContains(q));

        return ticketRepository.findAll(spec, pageable).map(this::toResponse);
    }

}
