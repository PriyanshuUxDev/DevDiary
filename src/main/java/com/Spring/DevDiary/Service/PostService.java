package com.Spring.DevDiary.Service;

import com.Spring.DevDiary.DTO.post.PostCreateRequestDTO;
import com.Spring.DevDiary.DTO.post.PostResponseDTO;
import com.Spring.DevDiary.Entity.Post;
import com.Spring.DevDiary.Exception.ResourceNotFoundException;
import com.Spring.DevDiary.Repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AiService aiService;

    // NEW: converts Entity -> DTO
    private PostResponseDTO toDTO(Post post) {
        return new PostResponseDTO(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getSummary(),
                post.getCategory() != null ? post.getCategory().getName() : null,
                post.getUser() != null ? post.getUser().getUserName() : null,
                post.getTags()
        );
    }

    public PostResponseDTO summarizePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));

        String prompt = """
            Summarize the following content in 1-2 sentences. Return only the summary.
            Content:
            %s
            """.formatted(post.getContent());

        String summary = aiService.summarize(prompt).trim();
        post.setSummary(summary);
        return toDTO(postRepository.save(post));
    }

    public PostResponseDTO generateTagsForPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        List<String> tags = aiService.generateTags(post.getContent());
        post.setTags(new ArrayList<>(tags));
        return toDTO(postRepository.save(post));
    }

    public PostResponseDTO createPost(PostCreateRequestDTO request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());

        String summary = aiService.summarize(post.getContent());
        post.setSummary(summary);
        return toDTO(postRepository.save(post));
    }

    public PostResponseDTO getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        return toDTO(post);
    }

    public List<PostResponseDTO> getAllPost() {
        return postRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }
}