package com.example.campushelp.service;

import com.example.campushelp.domain.Ticket;
import com.example.campushelp.domain.User;
import com.example.campushelp.domain.enums.TicketPriority;
import com.example.campushelp.repository.TicketRepository;
import com.example.campushelp.repository.UserRepository;
import com.example.campushelp.web.dto.AddCommentRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class TicketCommentServiceTest {

    @Autowired TicketCommentService ticketCommentService;
    @Autowired TicketRepository ticketRepository;
    @Autowired UserRepository userRepository;

    @Test
    void addComment_persistsComment() {
        User u = new User();
        u.setEmail("b@b.com");
        u.setFullName("B");
        u.setPasswordHash("x");
        u = userRepository.save(u);

        Ticket t = new Ticket();
        t.setTitle("T2");
        t.setDescription("D2");
        t.setCategory("Network");
        t.setPriority(TicketPriority.LOW);
        t.setRequester(u);
        t = ticketRepository.save(t);

        AddCommentRequest req = new AddCommentRequest();
        req.setAuthorId(u.getId());
        req.setBody("Hello");
        req.setInternal(false);

        var saved = ticketCommentService.add(t.getId(), req);
        assertThat(saved.getBody()).isEqualTo("Hello");

        var list = ticketCommentService.list(t.getId());
        assertThat(list).hasSize(1);
    }
}
