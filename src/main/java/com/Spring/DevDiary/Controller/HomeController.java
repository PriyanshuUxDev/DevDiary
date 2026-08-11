package com.Spring.DevDiary.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {

        Map<String, String> endpoints = new LinkedHashMap<>();

        endpoints.put("Health", "GET /health");

        // Authentication
        endpoints.put("Register", "POST /api/auth/register");

        // Categories
        endpoints.put("Create Category", "POST /api/category");
        endpoints.put("Get All Categories", "GET /api/category");

        // Posts
        endpoints.put("Create Post", "POST /api/post");
        endpoints.put("Get All Posts", "GET /api/post");
        endpoints.put("Get Post By Id", "GET /api/post/{id}");
        endpoints.put("Delete Post", "DELETE /api/post/{id}");
        endpoints.put("Summarize Post", "POST /api/post/{id}/summarize");
        endpoints.put("Generate Tags", "POST /api/post/{id}/tags");

        // AI
        endpoints.put("Ask Chatbot", "POST /api/gemini/ask");

        // Analytics
        endpoints.put("Get Analytics", "GET /api/analytics");

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("name", "DevDiary API");
        response.put("message", "Welcome to DevDiary API 🚀");
        response.put("version", "1.0");
        response.put("endpoints", endpoints);

        return response;
    }
}