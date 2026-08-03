package com.Spring.DevDiary.DTO.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String summary;
    private String categoryName;
    private String authorUserName;
    private List<String> tags;
}