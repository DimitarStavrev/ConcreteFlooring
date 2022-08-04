package com.example.theproject.service.impl;

import com.example.theproject.model.entity.Category;
import com.example.theproject.model.entity.enums.CategoryNameEnums;

public interface CategoryService {

     Category findCategoryByName(CategoryNameEnums categoryNameEnums);

    void initCategories();
}
