package com.server.finderly_backend.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {
    private String message;
    private String commentId;

    public CommentResponseDto(String message, String commentId) {
        this.message = message;
        this.commentId = commentId;
    }
}
