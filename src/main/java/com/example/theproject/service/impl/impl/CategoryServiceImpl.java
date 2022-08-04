package com.example.theproject.service.impl.impl;

import com.example.theproject.model.entity.Category;
import com.example.theproject.model.entity.enums.CategoryNameEnums;
import com.example.theproject.repository.CategoryRepository;
import com.example.theproject.service.impl.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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

    @Override
    public void initCategories() {

        if (categoryRepository.count() > 0){
            return;
        }
        Arrays.stream(CategoryNameEnums.values())
                .forEach(categoryNameEnums -> {
                    Category category = new Category();
                    category.setName(categoryNameEnums);

                    categoryRepository.save(category);
                });
    }
}
