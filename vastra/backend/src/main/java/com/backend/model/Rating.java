package com.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
class Rating{
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long ratingID;
     @ManyToOne
     @JoinColumn(name = "product_id")
     private Product product;
     private Long ratingNumber;
     private String description;
     @OneToOne
     @JoinColumn(name = "user_id")
     private User user;
}