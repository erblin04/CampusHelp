package com.example.campushelp.repositoy;

import com.example.campushelp.domain.Ticket;
import com.example.campushelp.domain.enums.TicketPriority;
import com.example.campushelp.domain.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Simple filtering (later we can upgrade to Specifications)
    List<Ticket> findByStatus(TicketStatus status);
    List<Ticket> findByPriority(TicketPriority priority);
    List<Ticket> findByCategoryIgnoreCase(String category);
}
