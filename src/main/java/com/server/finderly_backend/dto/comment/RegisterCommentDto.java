package com.server.finderly_backend.dto.comment;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterCommentDto {
    @NotBlank(message = "사용자 아이디를 입력해주세요.")
    private String userId;
    @NotBlank(message = "게시글 아이디를 입력해주세요.")
    private String postId;
    @NotNull(message = "게시판 카테고리를 입력해주세요.")
    private int postCategory;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    private Boolean secretCheck=false;
}
