package com.server.finderly_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LostDto {
    private String userId;
    private String lostName;
    private String lostLocation;
    private String lostDate;
    private String storage;
    private String description;
    private String[] pictures;
}
