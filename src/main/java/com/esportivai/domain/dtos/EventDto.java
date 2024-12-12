package com.esportivai.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long id;
    private String name;
    private Integer sportId;
    private String date;
    private String time;
    private String location;
    private Integer maxParticipants;
    private String skillLevel;

    public EventDto(Long id, String name, Integer sportId, String date, String time, String location, Integer maxParticipants) {
        this.id = id;
        this.name = name;
        this.sportId = sportId;
        this.date = date;
        this.time = time;
        this.location = location;
        this.maxParticipants = maxParticipants;
    }

}
