package com.server.finderly_backend.service;

import com.server.finderly_backend.controller.UserController;
import com.server.finderly_backend.data.entity.User;
import com.server.finderly_backend.data.repository.UserRepository;
import com.server.finderly_backend.dto.ResponseDto;
import com.server.finderly_backend.dto.user.UserProfileDto;
import com.server.finderly_backend.dto.user.UserSignupDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.server.finderly_backend.controller.SessionController.sessionList;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final HttpServletRequest httpServletRequest;

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
    @Transactional
    public ResponseEntity signUp(UserSignupDto userSignupDto) {

        try{
            userRepository.save(userSignupDto.toEntity());
            login(userSignupDto.getUserId(), userSignupDto.getUserPassword());
            return singUpResult(userSignupDto);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(new ResponseDto("회원가입 실패"),HttpStatus.BAD_REQUEST);
        }
    }

    // 회원가입 2
    // 비밀번호 암호화
//    public void signUpEncoded(UserSignupDto userSignupDto){
//        userRepository.save(userSignupDto.toEntity(passwordEncoder.encode(userSignupDto.getUserPassword())));
//    }

    public ResponseEntity singUpResult(UserSignupDto userSignupDto){
        LOGGER.info("[signup] userSignupDto: " + userSignupDto + " 시도");
        if (checkIfUserExists(userSignupDto.getUserId())) {
            LOGGER.info("회원가입 실패");
            return new ResponseEntity(new ResponseDto("이미 존재하는 아이디입니다."), HttpStatus.BAD_REQUEST);
        }
        if (checkIfNicknameExists(userSignupDto.getNickName())) {
            LOGGER.info("회원가입 실패");
            return new ResponseEntity(new ResponseDto("이미 존재하는 닉네임입니다."), HttpStatus.BAD_REQUEST);
        }
        signUp(userSignupDto);
        LOGGER.info("회원가입 성공");
        return new ResponseEntity(new ResponseDto("회원가입 성공"), HttpStatus.OK);
    }

    // 로그인
    public User login(String userId, String password) {
        Optional<User> optionalUser = userRepository.findByUserId(userId);

        // userId와 일치하는 User가 없으면 null return
        if(optionalUser.isEmpty()){
            return null;
        }

        User user = optionalUser.get();

        // 찾아온 User의 password와 입력된 password가 다르면 null return
        if(!user.getUserPassword().equals(password)){
            return null;
        }

        httpServletRequest.getSession().invalidate();
        HttpSession session = httpServletRequest.getSession(true);
        if(session==null){
            return null;
        }
        session.setAttribute("userId", userId);
        session.setMaxInactiveInterval(1800);
        sessionList.put(session.getId(), session);

        return user;
    }

    public ResponseEntity loginResult(String userId, String userPassword){
        LOGGER.info("[login] userId: " + userId + " userPassword: " + userPassword + " 시도");
        if (checkIfUserExists(userId)) {
            if (login(userId, userPassword) == null) {
                LOGGER.info("[login] 로그인 실패");
                return new ResponseEntity(new ResponseDto("비밀번호가 일치하지 않습니다."), HttpStatus.NOT_FOUND);
            }
            LOGGER.info("[login] 로그인 성공");
            return new ResponseEntity(new ResponseDto("로그인 성공"), HttpStatus.OK);
        }
        LOGGER.info("[login] 로그인 실패");
        return new ResponseEntity(new ResponseDto("존재하지 않는 아이디입니다."), HttpStatus.NOT_FOUND);
    }

    // id를 입력받아 User를 return 해주는 기능
    public User getUserById(Long userId) {
        if(userId == null) return null;

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }
    
    // userId를 입력받아 User를 return 해주는 기능
    public User getUserByUserId(String userId) {
        if(userId == null) return null;

        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }

    public ResponseEntity logout(String userId){
        LOGGER.info("[logout] userId: " + userId + " 시도");
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            String loginUserId = (String) session.getAttribute("userId");
            session.invalidate();
            sessionList.remove(session.getId());
            return new ResponseEntity(new ResponseDto(loginUserId + " 로그아웃 성공"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity(new ResponseDto("로그인 되어 있지 않습니다."), HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity profile(String userId){
        LOGGER.info("[profile] " + userId + " 사용자 프로필 조회");
        HttpSession session = httpServletRequest.getSession(false);

        if (session != null) {
            String loginUserId = (String) session.getAttribute("userId");
            User user = getUserByUserId(loginUserId);
            return new ResponseEntity(new UserProfileDto(user), HttpStatus.OK);
        }
        else{
            return new ResponseEntity(new ResponseDto("로그인 되어 있지 않습니다."), HttpStatus.NOT_FOUND);
        }
    }

}
