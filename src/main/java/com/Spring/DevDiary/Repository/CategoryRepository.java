package com.Spring.DevDiary.Repository;

import com.Spring.DevDiary.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByName(String name);
}
