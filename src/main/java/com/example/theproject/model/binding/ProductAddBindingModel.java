package com.example.theproject.model.binding;

import com.example.theproject.model.entity.Category;
import com.example.theproject.model.entity.User;
import com.example.theproject.model.entity.enums.CategoryNameEnums;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

public class ProductAddBindingModel {

    private String name;
    private String description;
    private BigDecimal price;
    private CategoryNameEnums category;

    public ProductAddBindingModel() {
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

    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryNameEnums getCategory() {
        return category;
    }

    public void setCategory(CategoryNameEnums category) {
        this.category = category;
    }
}
