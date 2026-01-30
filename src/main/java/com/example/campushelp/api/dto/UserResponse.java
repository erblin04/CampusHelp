package com.example.campushelp.api.dto;

import java.time.Instant;

public class UserResponse {

    private Long id;
    private String email;
    private String fullName;
    private boolean active;
    private Instant createdAt;

    public UserResponse(Long id, String email, String fullName, boolean active, Instant createdAt) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.active = active;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getFullName() { return fullName; }
    public boolean isActive() { return active; }
    public Instant getCreatedAt() { return createdAt; }
}
