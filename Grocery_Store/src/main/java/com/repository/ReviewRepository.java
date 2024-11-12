package com.repository;

import com.entity.Review;
import com.entity.Product;
import com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Find reviews by product
    List<Review> findByProduct(Product product);

    // Find reviews by user
    List<Review> findByUser(User user);

    // Find reviews by product id (useful if you want to fetch by product ID directly)
    List<Review> findByProductId(Long productId);

}
