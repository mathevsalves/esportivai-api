package com.esportivai.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SportDto {
    private Long id;
    private String name;
    private String skillLevel;
}
