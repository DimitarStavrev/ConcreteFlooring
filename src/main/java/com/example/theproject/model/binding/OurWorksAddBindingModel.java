package com.example.theproject.model.binding;

import com.example.theproject.model.entity.enums.CategoryNameEnums;

import javax.validation.constraints.Size;

public class OurWorksAddBindingModel {
    private String name;
    private String description;
    private CategoryNameEnums category;

    public OurWorksAddBindingModel() {
    }

    @Size(min = 3)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(min = 5)
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
}
