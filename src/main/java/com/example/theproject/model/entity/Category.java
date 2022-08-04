package com.example.theproject.model.entity;

import com.example.theproject.model.entity.enums.CategoryNameEnums;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    private CategoryNameEnums name;

    public Category() {
    }

   @Enumerated(EnumType.STRING)
    public CategoryNameEnums getName() {
        return name;
    }

    public void setName(CategoryNameEnums name) {
        this.name = name;
    }
}
