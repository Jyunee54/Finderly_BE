package com.server.finderly_backend.controller;

import com.server.finderly_backend.dto.ResponseDto;
import com.server.finderly_backend.dto.comment.RegisterCommentDto;
import com.server.finderly_backend.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity registerComment(@Valid @RequestBody RegisterCommentDto registerCommentDto) {
        return commentService.addComment(registerCommentDto);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteComment(@RequestParam String commentId) {
        return commentService.deleteComment(commentId);
    }

}
