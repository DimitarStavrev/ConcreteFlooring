package com.example.theproject.model.view;

import com.example.theproject.model.entity.Picture;

import java.util.Set;

public class OurWorkDetailsView {
    private Long id;
    private String name;
    private String description;
    private Set<Picture> pictures;

    public OurWorkDetailsView() {
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

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }
}
