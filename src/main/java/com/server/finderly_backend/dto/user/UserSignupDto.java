package com.server.finderly_backend.dto.user;

import com.server.finderly_backend.data.entity.User;
import com.server.finderly_backend.data.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupDto {

    @NotBlank(message = "로그인 아이디를 입력해주세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String userPassword;
    //private String passwordConfirm;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickName;

    // 비밀번호 암호화 X
    public User toEntity() {
        return User.builder()
                .userId(this.userId)
                .userPassword(userPassword)
                .nickName(this.nickName)
                .role(UserRole.USER)
                .build();
    }

    // 비밀번호 암호화
//    public User toEntity(String encodedPassword){
//        return User.builder()
//                .userId(this.userId)
//                .userPassword(encodedPassword)
//                .nickName(this.nickName)
//                .build();
//    }

}
