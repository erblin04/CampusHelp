package com.example.campushelp.service;

import com.example.campushelp.domain.Ticket;
import com.example.campushelp.domain.User;
import com.example.campushelp.domain.enums.TicketPriority;
import com.example.campushelp.domain.enums.TicketStatus;
import com.example.campushelp.repository.TicketRepository;
import com.example.campushelp.repository.TicketStatusHistoryRepository;
import com.example.campushelp.repository.UserRepository;
import com.example.campushelp.web.dto.ChangeStatusRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class TicketStatusServiceTest {

    @Autowired TicketStatusService ticketStatusService;
    @Autowired TicketRepository ticketRepository;
    @Autowired TicketStatusHistoryRepository historyRepository;
    @Autowired UserRepository userRepository;

    @Test
    void changeStatus_createsHistoryRow() {
        User u = new User();
        u.setEmail("a@a.com");
        u.setFullName("A");
        u.setPasswordHash("x");
        u = userRepository.save(u);

        Ticket t = new Ticket();
        t.setTitle("T1");
        t.setDescription("D1");
        t.setCategory("Network");
        t.setPriority(TicketPriority.HIGH);
        t.setRequester(u);
        t = ticketRepository.save(t);

        ChangeStatusRequest req = new ChangeStatusRequest();
        req.setChangedById(u.getId());
        req.setNewStatus(TicketStatus.IN_PROGRESS);

        ticketStatusService.changeStatus(t.getId(), req);

        assertThat(historyRepository.findByTicketIdOrderByChangedAtAsc(t.getId())).hasSize(1);
        assertThat(ticketRepository.findById(t.getId()).orElseThrow().getStatus()).isEqualTo(TicketStatus.IN_PROGRESS);
    }
}
