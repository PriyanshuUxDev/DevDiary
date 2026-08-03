package com.Spring.DevDiary.DTO.AI;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AutoTagResponseDTO {
    private Long postId;
    private List<String> tags;
}