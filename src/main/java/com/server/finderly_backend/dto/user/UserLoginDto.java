package com.server.finderly_backend.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NotBlank
@NoArgsConstructor
public class UserLoginDto {

    private String userId;
    private String userPassword;

    public UserLoginDto(UserSignupDto userSignupDto) {
        this.userId = userSignupDto.getUserId();
        this.userPassword = userSignupDto.getUserPassword();
    }

}
