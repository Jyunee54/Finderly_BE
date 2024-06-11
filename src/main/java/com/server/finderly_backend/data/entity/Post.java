package com.server.finderly_backend.data.entity;

import com.server.finderly_backend.dto.comment.CommentDto;
import com.server.finderly_backend.dto.post.RegisterPostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document
public class Post {
    @Id
    private String postId;
    private String userId;
    private String postTitle;
    private String postContent;
    private Boolean secretCheck=false;
    private List<String> pictures;
    private int commentsCnt = 0;
//    private List<CommentDto> comments = new ArrayList<>();

    public Post(RegisterPostDto registerPostDto) {
        this.userId = registerPostDto.getUserId();
        this.postTitle = registerPostDto.getPostTitle();
        this.postContent = registerPostDto.getPostContent();
        this.secretCheck = registerPostDto.getSecretCheck();
        this.pictures = registerPostDto.getPictures();
    }
}
