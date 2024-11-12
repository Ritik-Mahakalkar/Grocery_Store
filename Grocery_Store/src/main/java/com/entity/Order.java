package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private User user;

    private double totalPrice;  // Total price of the order
    private String status;      // e.g., "Pending", "Shipped", "Delivered"
    
    // List of products in the order (this could also be a separate entity, like OrderItem)
    private List<CartItem> items;

    // Default constructor
    public Order() {
    }

    // Constructor with all fields
    public Order(User user, double totalPrice, String status, List<CartItem> items) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.status = status;
        this.items = items;
    }

    // Getter and Setter methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", user=" + user + ", totalPrice=" + totalPrice + ", status=" + status + ", items="
                + items + "]";
    }
}
