package com.esportivai.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String name;
    private String birthDate;
    private String email;
    private String password;
    private String phone;
    @Column(name = "skill_level")
    private String skillLevel;
}
