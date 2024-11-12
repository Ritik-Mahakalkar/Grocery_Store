package com.controller;

import com.entity.Order;
import com.entity.User;
import com.service.OrderService;
import com.service.StripeService;
import com.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StripeService stripeService;

    @Autowired
    private UserService userService;

    // Place an order
    @PostMapping("/placeOrder")
    public Order placeOrder(@RequestParam Long userId, @RequestParam String discountCode) {
        User user = userService.getUserById(userId);
        return orderService.createOrder(user, discountCode);
    }

    // Process payment via Stripe
    @PostMapping("/pay")
    public String processPayment(@RequestParam Long userId, @RequestParam double amount) {
        User user = userService.getUserById(userId);
        boolean paymentSuccess = stripeService.processPayment(user, amount);
        if (paymentSuccess) {
            return "Payment successful!";
        } else {
            return "Payment failed!";
        }
    }
}
