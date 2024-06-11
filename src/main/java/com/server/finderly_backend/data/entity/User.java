package com.server.finderly_backend.data.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "user")
public class User {

    @Id
    private String _id;
    private String userId;
    private String userPassword;
    private String nickName;
    private int reports;
    private UserRole role;

    public User(String userId, String userPassword, String nickName) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.nickName = nickName;
        this.reports = 0;
        this.role = UserRole.USER;
    }

    public void increaseReports(){
        this.reports++;
    }
}
