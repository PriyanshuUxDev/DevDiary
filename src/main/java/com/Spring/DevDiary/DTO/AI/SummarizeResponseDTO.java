package com.Spring.DevDiary.DTO.AI;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SummarizeResponseDTO {
    private Long postId;
    private String summary;
}