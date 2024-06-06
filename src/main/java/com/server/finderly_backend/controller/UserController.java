package com.server.finderly_backend.controller;

import com.server.finderly_backend.data.entity.User;
import com.server.finderly_backend.dto.user.UserProfileDto;
import com.server.finderly_backend.dto.user.UserSignupDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.server.finderly_backend.service.UserService;
import com.server.finderly_backend.dto.ResponseDto;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserController.class);
    private final HttpServletRequest httpServletRequest;

    public UserController(UserService userService, HttpServletRequest httpServletRequest) {
        this.userService = userService;
        this.httpServletRequest = httpServletRequest;
    }

    // 로그인
    @GetMapping("login")
    public ResponseEntity userLogin(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "userPassword") String userPassword
    ) throws Exception {
        return userService.loginResult(userId, userPassword);
    }

    // 회원가입
    @PostMapping("signup")
    public ResponseEntity userSignup(@RequestBody UserSignupDto userSignupDto) {
        return userService.singUpResult(userSignupDto);
    }

    // 로그아웃
    @GetMapping("logout")
    public ResponseEntity userLogout(@RequestParam(value = "userId") String userId) {
        return userService.logout(userId);
    }

    // 사용자 조회
    @GetMapping("profile")
    public ResponseEntity userProfile(@RequestParam(value = "userId") String userId) {
        return userService.profile(userId);
    }
}
