package com.server.finderly_backend.dto.user;

import com.server.finderly_backend.data.entity.User;
import com.server.finderly_backend.dto.lost.FilteredLostDto;
import com.server.finderly_backend.dto.post.FilteredPostDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class UserProfileDto {

    // 응답 Dto
    private String userName;
    private int reports;
    private List<FilteredLostDto> founds = new ArrayList<>();
    private List<FilteredPostDto> lostPosts = new ArrayList<>();
    private List<FilteredPostDto> foundPosts = new ArrayList<>();

    public UserProfileDto(User user) {
        this.userName = user.getNickName();
        this.reports = user.getReports();
    }

    public UserProfileDto(String userName, int reports) {
        this.userName = userName;
        this.reports = reports;
    }

}
