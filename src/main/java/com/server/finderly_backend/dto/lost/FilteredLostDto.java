package com.server.finderly_backend.dto.lost;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilteredLostDto {
    private String lostId;
    private String lostName;
    private String lostLocation;
    private String storage;
}
