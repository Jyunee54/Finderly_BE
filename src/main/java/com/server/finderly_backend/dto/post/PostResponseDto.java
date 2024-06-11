package com.server.finderly_backend.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDto {
    private String message;
    private String postId;
    public PostResponseDto(String message, String postId) {
        this.message = message;
        this.postId = postId;
    }
}
