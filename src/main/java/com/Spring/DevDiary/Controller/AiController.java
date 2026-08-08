package com.Spring.DevDiary.Controller;

import com.Spring.DevDiary.DTO.AI.ChatRequestDTO;
import com.Spring.DevDiary.DTO.AI.ChatResponseDTO;
import com.Spring.DevDiary.Service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gemini")
@RequiredArgsConstructor
public class AiController {
    private final AiService aiService;

    @PostMapping("/summarize")
    public String summarize(@RequestBody String content) {
        String prompt = "Summarize the following blog post in 2-3 plain sentences. " +
                "Do not repeat or quote the title. Do not use quotation marks. " +
                "Start directly with the summary content:\n\n" + content;
        return aiService.summarize(prompt);
    }

    @PostMapping("/ask")
    public ChatResponseDTO ask(@RequestBody ChatRequestDTO request) {
        return aiService.answerQuestion(request.getQuestion());
    }

}
