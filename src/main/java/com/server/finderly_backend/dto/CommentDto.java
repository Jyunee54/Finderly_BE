package com.server.finderly_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private String userId;
    private String postId;
    private String content;
    private Boolean secretCheck;
}
