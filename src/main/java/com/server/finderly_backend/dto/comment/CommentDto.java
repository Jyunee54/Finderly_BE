package com.server.finderly_backend.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private String commentId;
    private String userId;
    private String content;
    private Boolean secretCheck;
}
