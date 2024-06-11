package com.server.finderly_backend.dto.lost;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegisterLostDto {
    @NotBlank(message = "사용자 아이디가 비어있습니다.")
    private String userId;
    @NotBlank(message = "분실물 이름을 입력해주세요.")
    private String lostName;
    @NotBlank(message = "분실물 위치를 입력해주세요.")
    private String lostLocation;
    @NotBlank(message = "분실 날짜를 입력해주세요.")
    private String lostDate;
    @NotBlank(message = "보관 장소를 입력해주세요.")
    private String storage;
    private String description;
    private List<String> pictures;
}
