package com.server.finderly_backend.dto.post;

import com.server.finderly_backend.data.entity.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilteredPostDto {
    private String postId;
    private String postTitle;
    private String postContent;

    public FilteredPostDto(Post post) {
        this.postId = post.getPostId();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
    }
}
