package com.server.finderly_backend.service;

import com.server.finderly_backend.data.entity.Comment;
import com.server.finderly_backend.data.repository.CommentRepository;
import com.server.finderly_backend.data.repository.UserRepository;
import com.server.finderly_backend.dto.ResponseDto;
import com.server.finderly_backend.dto.comment.CommentResponseDto;
import com.server.finderly_backend.dto.comment.RegisterCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostService postService;

    // 댓글 등록하기
    public ResponseEntity<?> addComment(RegisterCommentDto registerCommentDto){
        if(!userRepository.existsByUserId(registerCommentDto.getUserId())){
            return new ResponseEntity<>(new ResponseDto("등록되지 않은 사용자입니다."), HttpStatus.BAD_REQUEST);
        }
        if(!postService.existsByPostId(registerCommentDto.getPostCategory(), registerCommentDto.getPostId())){
            return new ResponseEntity<>(new ResponseDto("게시글이 존재하지 않습니다."),HttpStatus.BAD_REQUEST);
        }
        Comment savedComment = commentRepository.save(new Comment(registerCommentDto));
        return new ResponseEntity<>(new CommentResponseDto("댓글 등록 완료", savedComment.getCommentId()), HttpStatus.OK);
    }

    // 댓글 삭제하기
    public ResponseEntity<ResponseDto> deleteComment(String commentId){
        if(!commentRepository.existsById(commentId)){
            return new ResponseEntity<>(new ResponseDto("댓글이 존재하지 않습니다."),HttpStatus.BAD_REQUEST);
        }
        commentRepository.deleteById(commentId);
        return new ResponseEntity<>(new ResponseDto("댓글 삭제 완료"), HttpStatus.OK);
    }
}
