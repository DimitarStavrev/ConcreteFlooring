package com.example.theproject.service.impl.impl;

import com.example.theproject.exceptions.ObjectNotFoundException;
import com.example.theproject.model.entity.Product;
import com.example.theproject.model.entity.User;
import com.example.theproject.model.service.ProductServiceModel;
import com.example.theproject.model.view.ProductDetailsView;
import com.example.theproject.model.view.ProductViewModel;
import com.example.theproject.repository.ProductRepository;
import com.example.theproject.repository.UserRepository;
import com.example.theproject.service.impl.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
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
    public void addProduct(ProductServiceModel productServiceModel, UserDetails userDetails) {
        Product newProduct = modelMapper.map(productServiceModel, Product.class);

        User author = userRepository
                .findByUsername(userDetails.getUsername())
                .orElseThrow();

        newProduct.setUser(author);

        productRepository.save(newProduct);
    }

    @Override
    public ProductDetailsView findProductById(Long id) {
        return productRepository
                .findById(id)
                .map(product -> modelMapper.map(product, ProductDetailsView.class))
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }


}
