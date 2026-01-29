package com.example.campushelp.web.api;

import com.example.campushelp.web.dto.AddCommentRequest;
import com.example.campushelp.web.dto.CommentResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/tickets/{ticketId}/comments")
public interface TicketCommentApi {

    @PostMapping
    CommentResponse add(@PathVariable Long ticketId, @Valid @RequestBody AddCommentRequest req);

    @GetMapping
    List<CommentResponse> list(@PathVariable Long ticketId);

    @DeleteMapping("/{commentId}")
    void delete(@PathVariable Long ticketId, @PathVariable Long commentId);
}
