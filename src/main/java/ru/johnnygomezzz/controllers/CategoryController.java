package ru.johnnygomezzz.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.johnnygomezzz.error_handling.ResourceNotFoundException;
import ru.johnnygomezzz.models.Category;
import ru.johnnygomezzz.services.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public Category getOneCategoryById(@PathVariable Long id) {
        return categoryService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category doesn't exists: " + id));
    }

    @GetMapping()
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }
}
