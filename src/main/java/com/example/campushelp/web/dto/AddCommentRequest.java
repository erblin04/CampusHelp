package com.example.campushelp.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddCommentRequest {

    @NotNull
    private Long authorId;

    @NotBlank
    private String body;

    private boolean internal = false;

    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public boolean isInternal() { return internal; }
    public void setInternal(boolean internal) { this.internal = internal; }
}
