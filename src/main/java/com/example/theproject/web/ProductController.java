package com.example.theproject.web;

import com.example.theproject.model.binding.ProductAddBindingModel;
import com.example.theproject.model.service.ProductServiceModel;
import com.example.theproject.model.user.ProjetUserDetails;
import com.example.theproject.service.impl.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public String products(Model model) {
        model.addAttribute("products", productService.findAllProducts());
        return "products";
    }

    @ModelAttribute
    public ProductAddBindingModel productAddBindingModel() {
        return new ProductAddBindingModel();
    }

    @GetMapping("/add")
    public String add() {
        return "product-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid ProductAddBindingModel productAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal ProjetUserDetails projetUserDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("productAddBindingModel", productAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel",
                            bindingResult);

            return "redirect:/products/add";
        }

        ProductServiceModel productServiceModel = modelMapper.map(productAddBindingModel, ProductServiceModel.class);

        productService.addProduct(productServiceModel,projetUserDetails);

        return "redirect:/products";
    }


    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findProductById(id));
        return "product-details";
    }
}
