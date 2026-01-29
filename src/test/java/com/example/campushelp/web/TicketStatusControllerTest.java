package com.example.campushelp.web;

import com.example.campushelp.service.TicketStatusService;
import com.example.campushelp.domain.enums.TicketPriority;
import com.example.campushelp.domain.enums.TicketStatus;
import com.example.campushelp.web.dto.TicketResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TicketStatusController.class)
class TicketStatusControllerTest {

    @Autowired MockMvc mvc;
    @MockBean TicketStatusService ticketStatusService;

    @Test
    void changeStatus_returns200() throws Exception {
        when(ticketStatusService.changeStatus(eq(1L), any()))
                .thenReturn(new TicketResponse(
                        1L, "T", "D", "Network",
                        TicketPriority.HIGH, TicketStatus.IN_PROGRESS,
                        1L, "Req", null, null,
                        Instant.now(), Instant.now()
                ));

        mvc.perform(patch("/api/tickets/1/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"newStatus\":\"IN_PROGRESS\",\"changedById\":1}"))
                .andExpect(status().isOk());
    }
}
