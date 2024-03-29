package ru.johnnygomezzz.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.johnnygomezzz.models.Category;
import ru.johnnygomezzz.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
