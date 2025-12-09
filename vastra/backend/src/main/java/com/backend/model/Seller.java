package com.backend.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
class Seller{
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long sellerId;
   private String ownerName;
   private String panNumber, BrandName;
   private int brandRating;

   @OneToMany(mappedBy="seller" ,cascade = CascadeType.ALL, orphanRemoval = true )
   private List<Product>products = new ArrayList<>();
   
}