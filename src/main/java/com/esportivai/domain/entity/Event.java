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
    @Column(name = "event_id")
    private Long eventId;
    @Column(name = "event_name")
    private String eventName;
    private String description;
    @Column(name = "event_date")
    private LocalDate eventDate;
    @Column(name = "event_time")
    private LocalTime eventTime;
    private String location;
    @Column(name = "max_participants")
    private Integer maxParticipants;
    @Column(name = "skill_level")
    private String skillLevel;
    private String status;
    @Column(name = "organizer_id")
    private Integer organizerId;
}

