package com.service;

import com.entity.Order;
import com.entity.User;
import com.entity.CartItem;
import com.repository.OrderRepository;
import com.repository.CartItemRepository;
import com.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private DiscountRepository discountRepository;

    public Order createOrder(User user, String discountCode) {
        // Calculate the initial total price of the cart
        double initialTotal = cartItemRepository.findByUser(user).stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        // Use an array to store the total, allowing modification inside lambda
        final double[] total = {initialTotal};  // Using an array to allow modification inside lambda

        // Apply discount if available
        if (discountCode != null && !discountCode.isEmpty()) {
            discountRepository.findByCode(discountCode).ifPresent(discount -> {
                // Apply the discount to the total
                total[0] *= (1 - discount.getPercentage() / 100.0);
            });
        }

        // Create and save the order
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(total[0]);  // Set the updated total
        order.setStatus("Created");

        // Save the order in the repository
        Order savedOrder = orderRepository.save(order);

        // Clear the user's cart after the order is created
        cartItemRepository.deleteAll(cartItemRepository.findByUser(user));

        return savedOrder;
    }

    // Get all orders for a specific user
    public List<Order> getUserOrders(User user) {
        return orderRepository.findByUser(user);
    }

    // Get a specific order by its ID
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
