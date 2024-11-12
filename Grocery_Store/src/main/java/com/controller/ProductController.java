package com.controller;

import com.entity.Product;
import com.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get all products
    @GetMapping("/list")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get product by ID
    @GetMapping("/details/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    // Search products by category
    @GetMapping("/search")
    public List<Product> searchProductsByCategory(@RequestParam String category) {
        return productService.getProductsByCategory(category);
    }
}
