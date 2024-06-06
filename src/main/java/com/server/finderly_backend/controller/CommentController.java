package com.server.finderly_backend.controller;

import com.server.finderly_backend.dto.CommentDto;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("comment")
public class CommentController {

    @PostMapping
    public String addComment(@RequestParam String commentId) {
        return "Comment added";
    }

    @DeleteMapping
    public String deleteComment(@RequestBody CommentDto commentDto) {
        return "Comment deleted";
    }

}
