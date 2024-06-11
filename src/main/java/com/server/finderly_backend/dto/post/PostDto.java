package com.server.finderly_backend.dto.post;

import com.server.finderly_backend.data.entity.Post;
import com.server.finderly_backend.dto.comment.CommentDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDto {
    private String postId;
    private String postTitle;
    private String postContent;
    private List<String> pictures;
    private int commentsCnt;
    private List<CommentDto> comments;

    public PostDto(Post post) {
        this.postId = post.getPostId();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.pictures = post.getPictures();
        this.commentsCnt = post.getCommentsCnt();
    }
}
