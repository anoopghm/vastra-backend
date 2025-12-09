package com.backend.model;

 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
class Cart{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long cartId;
    @OneToOne
    @JoinColumn(name = "user")
    private User user;
    
}