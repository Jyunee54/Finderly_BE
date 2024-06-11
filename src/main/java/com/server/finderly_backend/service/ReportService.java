package com.server.finderly_backend.service;

import com.server.finderly_backend.data.entity.Lost;
import com.server.finderly_backend.data.entity.Post;
import com.server.finderly_backend.data.entity.User;
import com.server.finderly_backend.data.repository.LostRepository;
import com.server.finderly_backend.data.repository.UserRepository;
import com.server.finderly_backend.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final PostService postService;
    private final UserRepository userRepository;
    private final LostService lostService;
    private final LostRepository lostRepository;

    public ResponseEntity<ResponseDto> report(int category, String id, String userId){
        // 예외 처리
        if(id == null || userId == null || userId.isEmpty()){
            return new ResponseEntity<>(new ResponseDto("잘못된 입력입니다."), HttpStatus.BAD_REQUEST);
        }
        if(category == 1 || category == 0){
//            if(!postService.existsByPostId(category,id)){
//                return new ResponseEntity<>(new ResponseDto("해당 글이 존재하지 않습니다."),HttpStatus.BAD_REQUEST);
//            }
            Post post = postService.getPostById(category,id);
            if(post == null){
                return new ResponseEntity<>(new ResponseDto("해당 글이 존재하지 않습니다."),HttpStatus.BAD_REQUEST);
            }
            if(!post.getUserId().equals(userId)){
                return new ResponseEntity<>(new ResponseDto("사용자가 일치하지 않습니다."),HttpStatus.BAD_REQUEST);
            }
        }
        else if(category == 2){
//            if(!lostRepository.existsById(id)){
//                return new ResponseEntity<>(new ResponseDto("해당 게시글이 존재하지 않습니다."),HttpStatus.BAD_REQUEST);
//            }
            Optional<Lost> optionalLost = lostRepository.findById(id);
            if(optionalLost.isEmpty()){
                return new ResponseEntity<>(new ResponseDto("해당 게시글이 존재하지 않습니다."),HttpStatus.BAD_REQUEST);
            }
            if(!optionalLost.get().getUserId().equals(userId)){
                return new ResponseEntity<>(new ResponseDto("사용자가 일치하지 않습니다."),HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity<>(new ResponseDto("잘못된 요청입니다."),HttpStatus.BAD_REQUEST);
        }
        
        // 신고하기
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.increaseReports();
            userRepository.save(user);
            return new ResponseEntity<>(new ResponseDto("신고 완료"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDto("사용자가 존재하지 않습니다."),HttpStatus.BAD_REQUEST);
    }
}
