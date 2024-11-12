package com.controller;

import com.entity.CartItem;
import com.entity.Product;
import com.entity.User;
import com.service.CartService;
import com.service.ProductService;
import com.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    // Add item to cart
    @PostMapping("/add")
    public String addItemToCart(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        User user = userService.getUserById(userId);
        Product product = productService.getProductById(productId);
        cartService.addItemToCart(user, product, quantity);
        return "Item added to cart!";
    }

    // Remove item from cart
    @PostMapping("/remove")
    public String removeItemFromCart(@RequestParam Long userId, @RequestParam Long productId) {
        User user = userService.getUserById(userId);
        Product product = productService.getProductById(productId);
        cartService.removeItemFromCart(user, product);
        return "Item removed from cart!";
    }

    // View cart items
    @GetMapping("/view")
    public List<CartItem> viewCart(@RequestParam Long userId) {
        User user = userService.getUserById(userId);
        return cartService.viewCart(user);
    }
}
