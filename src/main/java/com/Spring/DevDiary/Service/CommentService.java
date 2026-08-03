package com.Spring.DevDiary.Service;

import com.Spring.DevDiary.Entity.Comment;
import com.Spring.DevDiary.Repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment saveComment(Comment Comment) {
        return commentRepository.save(Comment);
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(null);
//                orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + id));
    }




    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public void deleteCommentById(Long id){
        commentRepository.deleteById(id);
    }
}
