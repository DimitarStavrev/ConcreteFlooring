package com.example.theproject.service.impl;

import com.example.theproject.model.entity.Product;
import com.example.theproject.model.service.ProductServiceModel;
import com.example.theproject.model.view.ProductViewModel;
import com.example.theproject.repository.ProductRepository;
import com.example.theproject.service.CategoryService;
import com.example.theproject.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }


    @Override
    public List<ProductViewModel> findAllProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(product -> {
                    ProductViewModel productViewModel = modelMapper.map(product, ProductViewModel.class);
                    if (product.getPictures().isEmpty()) {
                        productViewModel.setPictureUrl("/images/no-image.jpg");
                    } else {
                        productViewModel.setPictureUrl(product.getPictures().stream().findFirst().get().getUrl());
                    }
                    return productViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {
        Product product = modelMapper.map(productServiceModel, Product.class);

       product.setCategory(categoryService.findCategoryByName(productServiceModel.getCategory()));
        productRepository.save(product);
    }
}
