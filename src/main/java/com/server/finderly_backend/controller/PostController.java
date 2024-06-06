package com.server.finderly_backend.controller;

import com.server.finderly_backend.dto.PostDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("post")
public class PostController {

    // 게시글 검색
    @GetMapping("search")
    public String searchPost(@RequestParam String keyword) {
        return "search";
    }

    // 게시글 리스트 조회
    @GetMapping
    public String getPostList(@RequestParam(value = "postCategory") int postCategory) {
        return "get";
    }

    // 게시글 상세 조회
    @GetMapping("detail")
    public String getPostDetail(
            @RequestParam(value = "postCategory") int postCategory,
            @RequestParam(value = "postId") String postId,
            @RequestParam(value = "userId") String userId
    ) {
        return "detail";
    }

    // 게시글 삭제
    @DeleteMapping
    public String deletePost(@RequestParam String postId) {
        return "delete " + postId;
    }

    // 게시글 등록
    @PostMapping
    public String registerPost(@RequestBody PostDto postDto) {
        return "post : " + postDto.toString();
    }

}
