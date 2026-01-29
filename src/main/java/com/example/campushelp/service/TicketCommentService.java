package com.example.campushelp.service;

import com.example.campushelp.domain.Ticket;
import com.example.campushelp.domain.TicketComment;
import com.example.campushelp.domain.User;
import com.example.campushelp.repository.TicketCommentRepository;
import com.example.campushelp.repository.TicketRepository;
import com.example.campushelp.web.dto.AddCommentRequest;
import com.example.campushelp.web.dto.CommentResponse;
import com.example.campushelp.web.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketCommentService {

    private final TicketRepository ticketRepository;
    private final TicketCommentRepository commentRepository;
    private final UserService userService;

    public TicketCommentService(TicketRepository ticketRepository,
                                TicketCommentRepository commentRepository,
                                UserService userService) {
        this.ticketRepository = ticketRepository;
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    public CommentResponse add(Long ticketId, AddCommentRequest req) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new NotFoundException("Ticket not found: " + ticketId));

        User author = userService.getEntity(req.getAuthorId());

        TicketComment c = new TicketComment();
        c.setTicket(ticket);
        c.setAuthor(author);
        c.setBody(req.getBody());
        c.setInternal(req.isInternal());

        return toResponse(commentRepository.save(c));
    }

    public List<CommentResponse> list(Long ticketId) {
        if (!ticketRepository.existsById(ticketId)) {
            throw new NotFoundException("Ticket not found: " + ticketId);
        }
        return commentRepository.findByTicketIdOrderByCreatedAtAsc(ticketId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private CommentResponse toResponse(TicketComment c) {
        return new CommentResponse(
                c.getId(),
                c.getTicket().getId(),
                c.getAuthor().getId(),
                c.getAuthor().getFullName(),
                c.getBody(),
                c.isInternal(),
                c.getCreatedAt()
        );
    }
}
