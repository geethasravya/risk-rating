package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BitsightScore {
    @Id
    private Long productId;
    private int rating;

    public BitsightScore() {}
    public BitsightScore(Long productId, int rating) {
        this.productId = productId;
        this.rating = rating;
    }

    // getters and setters omitted
}