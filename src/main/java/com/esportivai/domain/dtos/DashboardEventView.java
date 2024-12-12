package com.esportivai.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardEventView {
    private String name;
    private String date;
    private Integer sportId;
    private String skillLevel;


}
