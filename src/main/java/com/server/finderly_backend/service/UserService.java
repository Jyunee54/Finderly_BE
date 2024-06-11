package com.server.finderly_backend.service;

import com.server.finderly_backend.data.entity.User;
import com.server.finderly_backend.data.repository.LostRepository;
import com.server.finderly_backend.data.repository.UserRepository;
import com.server.finderly_backend.dto.ResponseDto;
import com.server.finderly_backend.dto.lost.FilteredLostDto;
import com.server.finderly_backend.dto.post.FilteredPostDto;
import com.server.finderly_backend.dto.user.UserProfileDto;
import com.server.finderly_backend.dto.user.UserSignupDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.server.finderly_backend.controller.SessionController.sessionList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final HttpServletRequest httpServletRequest;
    private final LostRepository lostRepository;
    private final PostService postService;

    // userId 중복 체크
    public boolean checkIfUserExists(String userId) {
        return userRepository.existsByUserId(userId);
    }

    // nickName 중복 체크
    public boolean checkIfNicknameExists(String nickname) {
        return userRepository.existsByNickName(nickname);
    }

    // 회원가입 1
    // 비밀번호 암호화 X
    public void signUp(UserSignupDto userSignupDto) {

        try {
            userRepository.save(userSignupDto.toEntity());
            login(userSignupDto.getUserId(), userSignupDto.getUserPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ResponseEntity<ResponseDto> singUpResult(UserSignupDto userSignupDto) {
        if (checkIfUserExists(userSignupDto.getUserId())) {
            return new ResponseEntity<>(new ResponseDto("이미 존재하는 아이디입니다."), HttpStatus.BAD_REQUEST);
        }
        if (checkIfNicknameExists(userSignupDto.getNickName())) {
            return new ResponseEntity<>(new ResponseDto("이미 존재하는 닉네임입니다."), HttpStatus.BAD_REQUEST);
        }
        signUp(userSignupDto);
        return new ResponseEntity<>(new ResponseDto("회원가입 성공"), HttpStatus.OK);
    }

    // 로그인
    public User login(String userId, String userPassword) {
        Optional<User> optionalUser = userRepository.findByUserId(userId);

        // userId와 일치하는 User가 없으면 null return
        if (optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        // 찾아온 User의 password와 입력된 password가 다르면 null return
        if (!user.getUserPassword().equals(userPassword)) {
            return null;
        }

        httpServletRequest.getSession().invalidate();
        HttpSession session = httpServletRequest.getSession(true);
        if (session == null) {
            return null;
        }
        session.setAttribute("userId", userId);
        session.setMaxInactiveInterval(1800);
        sessionList.put(session.getId(), session);

        return user;
    }

    public ResponseEntity<ResponseDto> loginResult(String userId, String userPassword) {
        if (checkIfUserExists(userId)) {
            if (login(userId, userPassword) == null) {
                return new ResponseEntity<>(new ResponseDto("비밀번호가 일치하지 않습니다."), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(new ResponseDto("로그인 성공"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDto("존재하지 않는 아이디입니다."), HttpStatus.NOT_FOUND);
    }

    // id를 입력받아 User를 return 해주는 기능
    public User getUserById(String userId) {
        if (userId == null) return null;

        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);

    }

    // userId를 입력받아 User를 return 해주는 기능
    public User getUserByUserId(String userId) {
        if (userId == null) return null;

        Optional<User> optionalUser = userRepository.findByUserId(userId);
        return optionalUser.orElse(null);

    }

    // 로그아웃
    public ResponseEntity<ResponseDto> logout(String userId) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            String loginUserId = (String) session.getAttribute("userId");
            session.invalidate();
            sessionList.remove(session.getId());
        }
        return new ResponseEntity<>(new ResponseDto("로그아웃 성공"), HttpStatus.OK);
//        HttpSession session = httpServletRequest.getSession(false);
//        if (session != null) {
//            String loginUserId = (String) session.getAttribute("userId");
//            session.invalidate();
//            sessionList.remove(session.getId());
//            return new ResponseEntity<>(new ResponseDto("로그아웃 성공"), HttpStatus.OK);
//        }
//        else{
//            return new ResponseEntity<>(new ResponseDto("로그인 되어 있지 않습니다."), HttpStatus.NOT_FOUND);
    }

    // 사용자 정보 조회
    public ResponseEntity<?> profile(String userId){
        User user = getUserByUserId(userId);
        if(user == null){
            return new ResponseEntity<>(new ResponseDto("올바르지 않은 사용자 아이디입니다."), HttpStatus.OK);
        }

        // 사용자가 찾은 분실물 추가
        UserProfileDto userProfileDto = new UserProfileDto(user);
        List<FilteredLostDto> userLostsFounds = lostRepository.findAllByUserId(userId);
        userProfileDto.setFounds(userLostsFounds);

        // 사용자가 작성한 게시글 추가
        List<FilteredPostDto> userLostPosts = postService.getPostListByCategory(0, userId);
        List<FilteredPostDto> userFoundPosts = postService.getPostListByCategory(1, userId);
        userProfileDto.setFoundPosts(userFoundPosts);
        userProfileDto.setLostPosts(userLostPosts);

        return new ResponseEntity<>(userProfileDto, HttpStatus.OK);
    }

}
