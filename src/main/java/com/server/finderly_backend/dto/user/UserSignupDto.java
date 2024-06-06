package com.server.finderly_backend.dto.user;

import com.server.finderly_backend.data.entity.User;
import com.server.finderly_backend.data.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSignupDto {

    @NotBlank(message = "로그인 아이디가 비어있습니다.")
    private String userId;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String userPassword;
    //private String passwordConfirm;

    @NotBlank(message = "닉네임이 비어있싑니다.")
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
