package com.server.finderly_backend.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegisterPostDto {
    @NotBlank(message = "사용자 아이디가 비어있습니다.")
    private String userId;
    @NotBlank(message = "제목을 입력해주세요.")
    private String postTitle;
    @NotBlank(message = "내용을 입력해주세요.")
    private String postContent;
    @NotNull(message = "게시글 카테고리가 비어있습니다.")
    private int postCategory=0;
    private Boolean secretCheck = false;
    private List<String> pictures;

}
