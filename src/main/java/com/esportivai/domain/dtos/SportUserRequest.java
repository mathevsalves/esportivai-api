package com.esportivai.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SportUserRequest {

    private Integer userId;
    private Integer sportId;
    private String skillLevel;
}
