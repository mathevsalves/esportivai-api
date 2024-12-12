package com.esportivai.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "sport_id")
    private Integer sportId;

    private LocalDate date;

    private LocalTime time;

    private String location;

    @Column(name = "max_participants")
    private Integer maxParticipants;

    @Column(name = "skill_level")
    private String skillLevel;



    @Column(name = "organizer_id")
    private Long organizerId;
}

