package com.controller;

import com.entity.Order;
import com.entity.User;
import com.service.OrderService;
import com.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    // View all orders for a user
    @GetMapping("/userOrders")
    public List<Order> getUserOrders(@RequestParam Long userId) {
        User user = userService.getUserById(userId);
        return orderService.getUserOrders(user);
    }

    // View a specific order by ID
    @GetMapping("/order/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    // Update order status
    @PostMapping("/updateStatus")
    public String updateOrderStatus(@RequestParam Long orderId, @RequestParam String status) {
        Order order = orderService.getOrderById(orderId);
        order.setStatus(status);
        orderService.updateOrder(order);
        return "Order status updated!";
    }
}
