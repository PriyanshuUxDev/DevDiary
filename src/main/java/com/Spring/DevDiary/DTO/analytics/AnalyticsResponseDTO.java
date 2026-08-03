package com.Spring.DevDiary.DTO.analytics;

import java.util.List;

public class AnalyticsResponseDTO {

    private List<CategoryCountDTO> postsPerCategory;
    private List<TopUserDTO> topUsers;
    private List<DailyCountDTO> postsPerDay;

    public AnalyticsResponseDTO() {}

    public AnalyticsResponseDTO(List<CategoryCountDTO> postsPerCategory,
                                List<TopUserDTO> topUsers,
                                List<DailyCountDTO> postsPerDay) {
        this.postsPerCategory = postsPerCategory;
        this.topUsers = topUsers;
        this.postsPerDay = postsPerDay;
    }

    public List<CategoryCountDTO> getPostsPerCategory() { return postsPerCategory; }
    public void setPostsPerCategory(List<CategoryCountDTO> postsPerCategory) { this.postsPerCategory = postsPerCategory; }

    public List<TopUserDTO> getTopUsers() { return topUsers; }
    public void setTopUsers(List<TopUserDTO> topUsers) { this.topUsers = topUsers; }

    public List<DailyCountDTO> getPostsPerDay() { return postsPerDay; }
    public void setPostsPerDay(List<DailyCountDTO> postsPerDay) { this.postsPerDay = postsPerDay; }
}