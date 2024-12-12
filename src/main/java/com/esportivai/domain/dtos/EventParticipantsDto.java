package com.esportivai.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventParticipantsDto {
    private Long id;
    private String name;
    private String date;
    private String time;
    private String location;
    private Integer maxParticipants;
}
