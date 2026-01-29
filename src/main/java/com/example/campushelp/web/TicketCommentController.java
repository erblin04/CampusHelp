package com.example.campushelp.web;

import com.example.campushelp.service.TicketCommentService;
import com.example.campushelp.web.dto.AddCommentRequest;
import com.example.campushelp.web.dto.CommentResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketCommentController {

    private final TicketCommentService ticketCommentService;

    public TicketCommentController(TicketCommentService ticketCommentService) {
        this.ticketCommentService = ticketCommentService;
    }

    @PostMapping("/{id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse add(@PathVariable Long id, @Valid @RequestBody AddCommentRequest req) {
        return ticketCommentService.add(id, req);
    }

    @GetMapping("/{id}/comments")
    public List<CommentResponse> list(@PathVariable Long id) {
        return ticketCommentService.list(id);
    }
}
