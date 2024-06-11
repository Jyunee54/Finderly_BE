package com.server.finderly_backend.data.entity;

import com.server.finderly_backend.dto.comment.RegisterCommentDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "comment")
public class Comment {
    @Id
    private String commentId;
    private String userId;
    private int postCategory;
    private String postId;
    private String content;
    private Boolean secretCheck;

    public Comment(RegisterCommentDto registerCommentDto) {
        this.userId = registerCommentDto.getUserId();
        this.postCategory = registerCommentDto.getPostCategory();
        this.postId = registerCommentDto.getPostId();
        this.content = registerCommentDto.getContent();
        this.secretCheck = registerCommentDto.getSecretCheck();
    }
}
