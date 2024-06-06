package com.server.finderly_backend.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NotBlank
public class UserLoginDto {

    private String userId;
    private String userPassword;

}
