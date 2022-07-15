package com.example.theproject.repository;

import com.example.theproject.model.entity.Category;
import com.example.theproject.model.entity.enums.CategoryNameEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(CategoryNameEnums name);
}
