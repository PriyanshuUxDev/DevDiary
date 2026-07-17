package com.Spring.DevDiary.Controller;
import com.Spring.DevDiary.Model.Post;
import com.Spring.DevDiary.Service.AiService;
import com.Spring.DevDiary.Service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostController {
    private PostService postService;

    @PostMapping("{id}/summarize")
//        public ResponseEntity<Post> summarize(@PathVariable Long id) {
    public Post summarize(@PathVariable Long id) {
            Post updated=postService.summarizePost(id);
        return updated;
//        return ResponseEntity.ok(updated);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @GetMapping
    public List<Post> getAll() {
        return postService.getAllPost();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        postService.deletePostById(id);
    }
    @PostMapping("{id}/tags")
    public Post generateTags(@PathVariable Long id) {
        return postService.generateTagsForPost(id);
    }
}
