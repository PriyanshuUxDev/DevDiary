package com.Spring.DevDiary.DTO.analytics;

public class DailyCountDTO {

    private String date;
    private Long count;

    public DailyCountDTO() {}

    public DailyCountDTO(String date, Long count) {
        this.date = date;
        this.count = count;
    }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public Long getCount() { return count; }
    public void setCount(Long count) { this.count = count; }
}