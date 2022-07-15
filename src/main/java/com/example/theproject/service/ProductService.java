package com.example.theproject.service;

import com.example.theproject.model.service.ProductServiceModel;
import com.example.theproject.model.view.ProductViewModel;

import java.util.List;

public interface ProductService {
    List<ProductViewModel> findAllProducts();

    void addProduct(ProductServiceModel productServiceModel);
}
