package com.Spring.DevDiary.DTO.analytics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AnalyticsResponseDTO {

    private List<CategoryCountDTO> postsPerCategory;
    private List<TopUserDTO> topUsers;
    private List<DailyCountDTO> postsPerDay;

}