package com.Spring.DevDiary.Repository;

import com.Spring.DevDiary.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT DISTINCT p FROM Post p LEFT JOIN p.tags t WHERE " +
            "LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(CAST(p.content AS string)) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Post> searchByKeyword(@Param("keyword") String keyword);


    @Query("SELECT p.category.name, COUNT(p) FROM Post p GROUP BY p.category.name")
    List<Object[]> countPostsByCategory();

    @Query("SELECT p.user.userName, COUNT(p) FROM Post p GROUP BY p.user.userName ORDER BY COUNT(p) DESC")
    List<Object[]> countPostsByUser();

    @Query("SELECT FUNCTION('DATE', p.createdAt), COUNT(p) FROM Post p GROUP BY FUNCTION('DATE', p.createdAt)")
    List<Object[]> countPostsByDay();

}