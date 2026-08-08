package com.Spring.DevDiary.Service;

import com.Spring.DevDiary.DTO.analytics.AnalyticsResponseDTO;
import com.Spring.DevDiary.DTO.analytics.CategoryCountDTO;
import com.Spring.DevDiary.DTO.analytics.DailyCountDTO;
import com.Spring.DevDiary.DTO.analytics.TopUserDTO;
import com.Spring.DevDiary.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final PostRepository postRepository;

    public AnalyticsResponseDTO getFullAnalytics() {
        List<CategoryCountDTO> postsPerCategory = postRepository.countPostsByCategory()
                .stream()
                .map(row -> new CategoryCountDTO(
                        row[0] != null ? (String) row[0] : "Uncategorized",
                        (Long) row[1]))
                .collect(Collectors.toList());

        List<TopUserDTO> topUsers = postRepository.countPostsByUser()
                .stream()
                .map(row -> new TopUserDTO(
                        row[0] != null ? (String) row[0] : "Unknown",
                        (Long) row[1]))
                .collect(Collectors.toList());

        List<DailyCountDTO> postsPerDay = postRepository.countPostsByDay()
                .stream()
                .filter(row -> row[0] != null)
                .map(row -> new DailyCountDTO(row[0].toString(), (Long) row[1]))
                .collect(Collectors.toList());

        return new AnalyticsResponseDTO(postsPerCategory, topUsers, postsPerDay);
    }
}