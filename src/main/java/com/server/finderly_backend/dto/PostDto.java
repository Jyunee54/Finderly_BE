package com.server.finderly_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
    private String userId;
    private String postTitle;
    private String postContent;
    private String postCategory;
    private Boolean secretCheck;
    private String[] pictures;
}
