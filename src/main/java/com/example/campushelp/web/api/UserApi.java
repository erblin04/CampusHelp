package com.example.campushelp.web.api;

import com.example.campushelp.web.dto.CreateUserRequest;
import com.example.campushelp.web.dto.UpdateUserRequest;
import com.example.campushelp.web.dto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/users")
public interface UserApi {

    @PostMapping
    UserResponse create(@Valid @RequestBody CreateUserRequest req);

    @GetMapping
    List<UserResponse> list();

    @GetMapping("/{id}")
    UserResponse getById(@PathVariable Long id);

    @PutMapping("/{id}")
    UserResponse update(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest req);

    @PatchMapping("/{id}/active")
    UserResponse setActive(@PathVariable Long id, @RequestParam boolean active);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}
