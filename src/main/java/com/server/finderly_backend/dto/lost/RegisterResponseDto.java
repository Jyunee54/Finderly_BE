package com.server.finderly_backend.dto.lost;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@NoArgsConstructor
public class RegisterResponseDto {
    private String message;
    private String lostId;

    public RegisterResponseDto(String message, String userId) {
        this.message = message;
        this.lostId = userId;
    }
}
