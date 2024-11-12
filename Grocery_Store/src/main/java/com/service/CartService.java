package com.service;

import com.entity.CartItem;
import com.entity.Product;
import com.entity.User;
import com.repository.CartItemRepository;
import com.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    // Add item to cart
    public void addItemToCart(User user, Long productId, int quantity) {
    	Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        CartItem cartItem = cartItemRepository.findByUserAndProduct_Id(user, product.getId());

        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }

        cartItemRepository.save(cartItem);
    }

    // Remove item from cart
    public void removeItemFromCart(User user, Product product) {
        CartItem cartItem = cartItemRepository.findByUserAndProduct_Id(user, product.getId());
        if (cartItem != null) {
            cartItemRepository.delete(cartItem);
        }
    }

    // Calculate total cart price
    public double calculateTotal(User user) {
        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        return cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    // Clear user's cart
    public void clearCart(User user) {
        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        cartItemRepository.deleteAll(cartItems);
    }

    // View cart items
    public List<CartItem> viewCart(User user) {
        return cartItemRepository.findByUser(user);
    }
}
