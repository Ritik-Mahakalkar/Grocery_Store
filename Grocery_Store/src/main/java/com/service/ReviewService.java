package com.service;

import com.entity.Review;
import com.entity.User;
import com.entity.Product;
import com.repository.ReviewRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    // Add a review for a specific product by a user
    public Review addReview(User user, Long productId, String comment, int rating) {
        Product product = new Product(); // Assuming this is retrieved from the DB
        product.setId(productId);

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setComment(comment);
        review.setRating(rating);

        return reviewRepository.save(review);
    }

    // Get reviews for a specific product
    public List<Review> getReviewsForProduct(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    // Get reviews made by a specific user
    public List<Review> getReviewsByUser(User user) {
        return reviewRepository.findByUser(user);
    }
}
