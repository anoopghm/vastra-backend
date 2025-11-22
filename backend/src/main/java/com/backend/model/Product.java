package com.backend.model;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;

@Entity
class Product{
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   Long productId;
   String productCategory;
   String productGender;
   String productFabric;
   String productFit;
   String productManufacturer;

   @ElementCollection
   @CollectionTable(
        name = "product_size_quantity",
        joinColumns = @JoinColumn(name = "product_id")
    )
    @MapKeyColumn(name = "size")
    @Column(name = "quantity")
    private Map<String, Integer> sizeToQuantity = new HashMap<>();

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
    

}