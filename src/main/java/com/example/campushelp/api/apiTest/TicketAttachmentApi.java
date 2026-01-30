//package com.example.campushelp.web.api;
//
//import com.example.campushelp.web.dto.AddAttachmentRequest;
//import com.example.campushelp.web.dto.AttachmentResponse;
//import jakarta.validation.Valid;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequestMapping("/api/tickets/{ticketId}/attachments")
//public interface TicketAttachmentApi {
//
//    @PostMapping
//    AttachmentResponse add(@PathVariable Long ticketId, @Valid @RequestBody AddAttachmentRequest req);
//
//    @GetMapping
//    List<AttachmentResponse> list(@PathVariable Long ticketId);
//
//    @DeleteMapping("/{attachmentId}")
//    void delete(@PathVariable Long ticketId, @PathVariable Long attachmentId);
//}
