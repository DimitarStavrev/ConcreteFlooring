package com.example.theproject.service.impl;

import com.example.theproject.model.service.ProductServiceModel;
import com.example.theproject.model.user.ProjetUserDetails;
import com.example.theproject.model.view.ProductDetailsView;
import com.example.theproject.model.view.ProductViewModel;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ProductService {
    List<ProductViewModel> findAllProducts();

    void addProduct(ProductServiceModel productServiceModel, UserDetails userDetails);

    ProductDetailsView findProductById(Long id);

}
