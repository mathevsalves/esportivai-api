package com.esportivai.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Event event;
}