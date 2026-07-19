package com.Spring.DevDiary.Service;

import com.Spring.DevDiary.Model.Post;
import com.Spring.DevDiary.Repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private AiService aiService;

    public Post summarizePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        String prompt = """
            You are a blog post summarizer.

            Summarize the following content in exactly 1 or 2 sentences.

            Rules:
            - Return only the summary.
            - Do not include headings.
            - Do not include labels like "Summary:".
            - Do not include greetings, introductions, or explanations.
            - Do not mention phrases like "Here is the summary" or "The post says".
            - Do not use bullet points.
            - Do not repeat the prompt.

            Content:
            %s
            """.formatted(post.getContent());

        String summary = aiService.summarize(prompt).trim();
        post.setSummary(summary);
        return postRepository.save(post);
    }

    public Post generateTagsForPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found: " + id));
        List<String> tags = aiService.generateTags(post.getContent());
        post.setTags(new ArrayList<>(tags));
        return postRepository.save(post);
    }
    public Post createPost(Post post){
        String summary = aiService.summarize(post.getContent());

//        String category=aiService.categorize(post.getContent());
        post.setSummary(summary);
//        post.setCategory(category);

        return postRepository.save(post);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    public void deletePostById(Long id){
        postRepository.deleteById(id);
    }

}
