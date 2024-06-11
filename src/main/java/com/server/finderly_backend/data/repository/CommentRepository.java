package com.server.finderly_backend.data.repository;

import com.server.finderly_backend.data.entity.Comment;
import com.server.finderly_backend.dto.comment.CommentDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<CommentDto> findCommentsByPostId(String postId);
}
