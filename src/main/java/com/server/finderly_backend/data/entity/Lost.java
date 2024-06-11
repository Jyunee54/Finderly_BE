package com.server.finderly_backend.data.entity;

import com.server.finderly_backend.dto.lost.RegisterLostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "lost")
public class Lost {

    @Id
    private String lostId;
    private String userId;
    private String lostName;
    private String lostLocation;
    private String lostDate;
    private String storage;
    private String description;
    private List<String> pictures;

    public Lost(RegisterLostDto registerLostDto){
        this.userId = registerLostDto.getUserId();
        this.lostName = registerLostDto.getLostName();
        this.lostLocation = registerLostDto.getLostLocation();
        this.lostDate = registerLostDto.getLostDate();
        this.storage = registerLostDto.getStorage();
        this.description = registerLostDto.getDescription();
        this.pictures = registerLostDto.getPictures();
    }

}
