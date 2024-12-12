package com.esportivai.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserResponse {
    private Long userId;
    private String name;
    private String email;

}
