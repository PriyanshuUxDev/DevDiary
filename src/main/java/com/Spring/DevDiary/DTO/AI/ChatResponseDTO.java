package com.Spring.DevDiary.DTO.AI;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class ChatResponseDTO {

    private String question;
    private String answer;
    private List<String> sources;



    public ChatResponseDTO(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }


}