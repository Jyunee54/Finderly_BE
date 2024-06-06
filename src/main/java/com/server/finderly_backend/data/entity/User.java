package com.server.finderly_backend.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String nickName;

    @Column(columnDefinition = "int default 0")
    private int reports;

    private UserRole role;

    public User(String userId, String userPassword, String nickName) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.nickName = nickName;
    }

}
