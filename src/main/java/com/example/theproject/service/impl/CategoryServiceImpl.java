package com.example.theproject.service.impl;

import com.example.theproject.model.entity.Category;
import com.example.theproject.model.entity.enums.CategoryNameEnums;
import com.example.theproject.repository.CategoryRepository;
import com.example.theproject.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findCategoryByName(CategoryNameEnums categoryNameEnums) {
        return categoryRepository
                .findByName(categoryNameEnums);
    }
}
