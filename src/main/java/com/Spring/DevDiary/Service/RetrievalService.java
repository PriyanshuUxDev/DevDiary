package com.Spring.DevDiary.Service;

import com.Spring.DevDiary.Model.Post;
import com.Spring.DevDiary.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RetrievalService {

    private final PostRepository postRepository;
    private final KeyWordExtraction keyWordExtraction;

    public List<Post> retrieveRelevantPosts(String question) {
        List<String> keywords = keyWordExtraction.extract(question);

        Set<Post> matchedPosts = new LinkedHashSet<>(); // avoids duplicates, keeps order

        for (String keyword : keywords) {
            matchedPosts.addAll(postRepository.searchByKeyword(keyword));
        }

        // Limit to top 3 posts so we don't overload Gemini's prompt
        return matchedPosts.stream().limit(3).collect(Collectors.toList());
    }
}