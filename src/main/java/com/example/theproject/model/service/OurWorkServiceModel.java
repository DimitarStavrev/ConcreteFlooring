package com.example.theproject.model.service;

import com.example.theproject.model.entity.Picture;
import com.example.theproject.model.entity.enums.CategoryNameEnums;

import java.util.Set;

public class OurWorkServiceModel {
    private Long id;
    private String name;
    private String description;
    private CategoryNameEnums category;
    private Set<Picture> pictures;

    public OurWorkServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryNameEnums getCategory() {
        return category;
    }

    public void setCategory(CategoryNameEnums category) {
        this.category = category;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }
}
