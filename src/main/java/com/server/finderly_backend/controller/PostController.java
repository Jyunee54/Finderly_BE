package com.server.finderly_backend.controller;

import com.server.finderly_backend.dto.ResponseDto;
import com.server.finderly_backend.dto.post.FilteredPostDto;
import com.server.finderly_backend.dto.post.RegisterPostDto;
import com.server.finderly_backend.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
@Validated
public class PostController {

    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    // 게시글 검색
    @GetMapping("search")
    public ResponseEntity<?> searchPost(
            @RequestParam(value = "postCategory") int postCategory,
            @RequestParam(value = "keyword") String keyword
    ) {
        if(keyword == null || keyword.isEmpty()){
            return new ResponseEntity<>(new ResponseDto("검색어를 입력해주세요."), HttpStatus.BAD_REQUEST);
        }
        return postService.searchByPostName(postCategory,keyword);
    }

    // 게시글 리스트 조회
    @GetMapping
    public ResponseEntity<List<FilteredPostDto>> getPostList(@RequestParam(value = "postCategory") int postCategory) {
        return postService.getAllPostList(postCategory);
    }

    // 게시글 상세 조회
    @GetMapping("detail")
    public ResponseEntity<?> getPostDetail(
            @RequestParam(value = "postCategory") int postCategory,
            @RequestParam(value = "postId") String postId
    ) {
        return postService.getPost(postCategory, postId);
    }

    // 게시글 삭제
    @DeleteMapping
    public ResponseEntity<ResponseDto> deletePost(
            @RequestParam int postCategory,
            @RequestParam String postId
    ) {
        return postService.deletePost(postCategory, postId);
    }

    // 게시글 등록
    @PostMapping(value = "register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerPost(@Valid @RequestBody RegisterPostDto registerPostDto) {
        return postService.addPost(registerPostDto);
    }

}
