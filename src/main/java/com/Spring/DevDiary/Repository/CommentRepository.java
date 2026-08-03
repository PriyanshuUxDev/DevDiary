package com.Spring.DevDiary.Repository;

import com.Spring.DevDiary.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {


}
