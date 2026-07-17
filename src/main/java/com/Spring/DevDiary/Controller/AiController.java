package com.Spring.DevDiary.Controller;

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
        String prompt = "Summarize the following blog post in 2-3 sentences:\n\n" + content;
        return aiService.summarize(prompt);
    }

}
