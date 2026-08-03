package com.Spring.DevDiary.DTO.AI;

import jakarta.validation.constraints.NotBlank;

public class ChatRequestDTO {

    @NotBlank(message = "Question cannot be empty")
    private String question;

    public ChatRequestDTO() {}

    public ChatRequestDTO(String question) {
        this.question = question;
    }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
}