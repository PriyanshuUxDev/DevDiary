package com.Spring.DevDiary.Service;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class KeyWordExtraction {
    private static final Set<String> STOP_WORDS = Set.of(
            "what", "is", "the", "a", "an", "did", "does", "how", "about",
            "in", "on", "of", "to", "and", "for", "tell", "me", "explain");
    public List<String> extract(String question) {
        return Arrays.stream(question.toLowerCase().split("\\s+"))
                .map(w -> w.replaceAll("[^a-z0-9]", ""))
                .filter(w -> !w.isEmpty() && !STOP_WORDS.contains(w))
                .distinct()
                .collect(Collectors.toList());
    }

}
