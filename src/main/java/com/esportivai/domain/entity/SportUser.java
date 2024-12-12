package com.esportivai.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "sportuser")
@NoArgsConstructor
public class SportUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "sport_id", nullable = false)
    private Sport sportId;

    @Column(nullable = false)
    private String skillLevel;

    public SportUser(User userId, Sport sportId, String skillLevel) {
        this.userId = userId;
        this.sportId = sportId;
        this.skillLevel = skillLevel;
    }
}