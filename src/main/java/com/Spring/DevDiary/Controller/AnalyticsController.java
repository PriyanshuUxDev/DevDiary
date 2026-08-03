package com.Spring.DevDiary.Controller;

import com.Spring.DevDiary.DTO.analytics.AnalyticsResponseDTO;
import com.Spring.DevDiary.DTO.analytics.CategoryCountDTO;
import com.Spring.DevDiary.DTO.analytics.DailyCountDTO;
import com.Spring.DevDiary.DTO.analytics.TopUserDTO;
import com.Spring.DevDiary.Service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/api/analytics")
    public AnalyticsResponseDTO getAnalytics() {
        return analyticsService.getFullAnalytics();
    }
}
