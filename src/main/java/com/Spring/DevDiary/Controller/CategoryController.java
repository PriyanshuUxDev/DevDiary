package com.Spring.DevDiary.Controller;

import com.Spring.DevDiary.Entity.Category;
import com.Spring.DevDiary.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAllCategories();
    }
}