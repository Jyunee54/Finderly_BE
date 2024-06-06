package com.server.finderly_backend.dto.user;

import com.server.finderly_backend.data.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class UserProfileDto {

    // 응답 Dto
    private String message;
    private String userName;
    private int reports;
    private List<UserFoundsDto> founds;
    private List<UserLostPostsDto> lostPosts;
    private List<UserFoundPostsDto> foundPosts;

    public UserProfileDto(String message, String userName, int reports) {
        this.message = "사용자 정보 조회 완료";
        this.message = message;
        this.userName = userName;
        this.reports = reports;
        this.founds = new ArrayList<>();
        this.lostPosts = new ArrayList<>();
        this.foundPosts = new ArrayList<>();
    }

    public UserProfileDto(User user) {
        this.message = "사용자 정보 조회 완료";
        this.userName = user.getNickName();
        this.reports = user.getReports();
        this.founds = new ArrayList<>();
        this.lostPosts = new ArrayList<>();
        this.foundPosts = new ArrayList<>();
    }

}
