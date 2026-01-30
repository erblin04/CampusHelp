package com.example.campushelp.service;

import com.example.campushelp.domain.Ticket;
import com.example.campushelp.domain.TicketStatusHistory;
import com.example.campushelp.domain.User;
import com.example.campushelp.domain.enums.TicketStatus;
import com.example.campushelp.repository.TicketRepository;
import com.example.campushelp.repository.TicketStatusHistoryRepository;
import com.example.campushelp.api.dto.ChangeStatusRequest;
import com.example.campushelp.api.dto.StatusHistoryResponse;
import com.example.campushelp.api.dto.TicketResponse;
import com.example.campushelp.api.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketStatusService {

    private final TicketRepository ticketRepository;
    private final TicketStatusHistoryRepository historyRepository;
    private final UserService userService;
    private final TicketService ticketService; // reuse mapping to TicketResponse

    public TicketStatusService(TicketRepository ticketRepository,
                               TicketStatusHistoryRepository historyRepository,
                               UserService userService,
                               TicketService ticketService) {
        this.ticketRepository = ticketRepository;
        this.historyRepository = historyRepository;
        this.userService = userService;
        this.ticketService = ticketService;
    }

    @Transactional
    public TicketResponse changeStatus(Long ticketId, ChangeStatusRequest req) {
        Ticket t = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new NotFoundException("Ticket not found: " + ticketId));

        User changedBy = userService.getEntity(req.getChangedById());

        TicketStatus oldStatus = t.getStatus();
        TicketStatus newStatus = req.getNewStatus();

        t.setStatus(newStatus);
        ticketRepository.save(t);

        TicketStatusHistory h = new TicketStatusHistory();
        h.setTicket(t);
        h.setChangedBy(changedBy);
        h.setOldStatus(oldStatus);
        h.setNewStatus(newStatus);
        historyRepository.save(h);

        return ticketService.getById(ticketId);
    }

    public List<StatusHistoryResponse> history(Long ticketId) {
        if (!ticketRepository.existsById(ticketId)) {
            throw new NotFoundException("Ticket not found: " + ticketId);
        }
        return historyRepository.findByTicketIdOrderByChangedAtAsc(ticketId)
                .stream()
                .map(h -> new StatusHistoryResponse(
                        h.getId(),
                        h.getTicket().getId(),
                        h.getOldStatus(),
                        h.getNewStatus(),
                        h.getChangedBy().getId(),
                        h.getChangedBy().getFullName(),
                        h.getChangedAt()
                ))
                .collect(Collectors.toList());
    }
}
