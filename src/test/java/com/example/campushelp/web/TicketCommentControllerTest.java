package com.example.campushelp.web;

import com.example.campushelp.service.TicketCommentService;
import com.example.campushelp.web.dto.CommentResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TicketCommentController.class)
class TicketCommentControllerTest {

    @Autowired MockMvc mvc;
    @MockBean TicketCommentService ticketCommentService;

    @Test
    void addComment_returns201() throws Exception {
        when(ticketCommentService.add(eq(1L), any()))
                .thenReturn(new CommentResponse(10L, 1L, 2L, "User", "Hi", false, Instant.now()));

        mvc.perform(post("/api/tickets/1/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"authorId\":2,\"body\":\"Hi\",\"internal\":false}"))
                .andExpect(status().isCreated());
    }
}
