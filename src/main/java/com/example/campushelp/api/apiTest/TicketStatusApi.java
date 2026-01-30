//package com.example.campushelp.web.api;
//
//import com.example.campushelp.web.dto.ChangeStatusRequest;
//import com.example.campushelp.web.dto.StatusHistoryResponse;
//import com.example.campushelp.web.dto.TicketResponse;
//import jakarta.validation.Valid;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequestMapping("/api/tickets/{ticketId}")
//public interface TicketStatusApi {
//
//    @PatchMapping("/status")
//    TicketResponse changeStatus(@PathVariable Long ticketId, @Valid @RequestBody ChangeStatusRequest req);
//
//    @GetMapping("/history")
//    List<StatusHistoryResponse> history(@PathVariable Long ticketId);
//}
