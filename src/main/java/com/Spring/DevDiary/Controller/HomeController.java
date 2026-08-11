package com.Spring.DevDiary.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class HomeController {
    
        @GetMapping("/")
        public ResponseEntity<Map<String, Object>> home() {

            Map<String, Object> response = new LinkedHashMap<>();

            response.put("name", "DevDiary API");
            response.put("message", "Welcome to DevDiary API 🚀");
            response.put("version", "1.0");

            Map<String, String> endpoints = new LinkedHashMap<>();

            endpoints.put("Health", "GET /health");
            endpoints.put("Blogs", "GET /api/blogs");
            endpoints.put("Get Blog", "GET /api/blogs/{id}");
            endpoints.put("Create Blog", "POST /api/blogs");
            endpoints.put("Update Blog", "PUT /api/blogs/{id}");
            endpoints.put("Delete Blog", "DELETE /api/blogs/{id}");
            endpoints.put("Ask AI", "POST /api/ask");

            response.put("endpoints", endpoints);

            return ResponseEntity.ok(response);
        }

        @GetMapping("/health")
        public ResponseEntity<String> health() {
            return ResponseEntity.ok("UP");
        }

}