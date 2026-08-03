package com.Spring.DevDiary.Controller;

import com.Spring.DevDiary.DTO.post.PostCreateRequestDTO;
import com.Spring.DevDiary.DTO.post.PostResponseDTO;
import com.Spring.DevDiary.Entity.Post;
import com.Spring.DevDiary.Service.PostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostController {
    private PostService postService;

    @PostMapping("{id}/summarize")
    public PostResponseDTO summarize(@PathVariable Long id) {
        return postService.summarizePost(id);
    }

    @PostMapping
    public PostResponseDTO createPost(@Valid @RequestBody PostCreateRequestDTO request) {
        return postService.createPost(request);
    }

    @GetMapping("/{id}")
    public PostResponseDTO getById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @GetMapping
    public List<PostResponseDTO> getAll() {
        return postService.getAllPost();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        postService.deletePostById(id);
    }

    @PostMapping("{id}/tags")
    public PostResponseDTO generateTags(@PathVariable Long id) {
        return postService.generateTagsForPost(id);
    }
}