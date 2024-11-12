package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private User user;

    private String comment;
    private int rating; // Assuming a rating between 1-5

    public Review(Long id, Product product, User user, String comment, int rating) {
		super();
		this.id = id;
		this.product = product;
		this.user = user;
		this.comment = comment;
		this.rating = rating;
	}

	// Constructors, Getters, Setters

    @Override
	public String toString() {
		return "Review [id=" + id + ", product=" + product + ", user=" + user + ", comment=" + comment + ", rating="
				+ rating + "]";
	}

	public Review() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
