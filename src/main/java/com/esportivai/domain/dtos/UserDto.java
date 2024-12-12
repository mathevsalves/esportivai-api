package com.esportivai.domain.dtos;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;
    private String password;
    private String skillLevel;

    public UserDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
