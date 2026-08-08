package com.Spring.DevDiary.Service;

import com.Spring.DevDiary.DTO.AI.ChatResponseDTO;
import com.Spring.DevDiary.Entity.Post;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AiService {
    @Value("${gemini.model:gemini-2.5-flash}")
    private String model;
    private final Client client;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String askAI(String prompt) {
        GenerateContentResponse response =
                client.models.generateContent(model, prompt, null);
        return response.text();
    }

    public String summarize(String content) {
        try {
            return askAI(content);
        } catch (Exception e) {
            log.error("Gemini error during summarize: {}", e.getMessage());
            return "Summary not available right now. Try /summarize later.";
        }
    }

    public List<String> generateTags(String content) {
        String prompt = """
                You are a tagging assistant. Read the blog post content below and
                return 3 to 5 short, relevant tags (single words or short phrases).

                Respond with ONLY valid JSON in this exact format, no markdown, no explanation:
                { "tags": ["tag1", "tag2", "tag3"] }

                Post content:
                """ + content;
        try {
            GenerateContentConfig config = GenerateContentConfig.builder()
                    .responseMimeType("application/json")
                    .build();

            GenerateContentResponse response =
                    client.models.generateContent(model, prompt, config);
            return extractTags(response.text());
        } catch (Exception e) {
            log.error("Gemini error during tagging: {}", e.getMessage());
            return new ArrayList<>(List.of("uncategorized"));
        }
    }

    private List<String> extractTags(String rawJson) {
        try {
            String cleaned = rawJson.replaceAll("```json", "").replaceAll("```", "").trim();
            JsonNode root = objectMapper.readTree(cleaned);
            JsonNode tagsNode = root.path("tags");

            List<String> tags = new ArrayList<>();
            tagsNode.forEach(t -> tags.add(t.asText()));
            return tags.isEmpty() ? List.of("uncategorized") : tags;
        } catch (Exception e) {
            log.error("Failed to parse Gemini tags JSON: {}", e.getMessage());
            return new ArrayList<>(List.of("uncategorized"));
        }
    }

    private final RetrievalService retrievalService;

    public ChatResponseDTO answerQuestion(String question) {
        List<Post> relevantPosts = retrievalService.retrieveRelevantPosts(question);

        if (relevantPosts.isEmpty()) {
            return new ChatResponseDTO(question, "I couldn't find anything relevant in the posts to answer that.");
        }

        StringBuilder context = new StringBuilder();
        for (Post post : relevantPosts) {
            context.append("Title: ").append(post.getTitle())
                    .append("\nContent: ").append(post.getContent()).append("\n\n");
        }

        String prompt = """
        Answer the question using ONLY the context below. If the answer isn't in the context, say so.

        Context:
        %s

        Question: %s
        """.formatted(context.toString(), question);

        String answer = askAI(prompt);
        return new ChatResponseDTO(question, answer);
    }
}