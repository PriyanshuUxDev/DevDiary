package com.Spring.DevDiary.DTO.analytics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DailyCountDTO {

    private String date;
    private Long count;
}