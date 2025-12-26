package com.backend.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productCategory;
    private String productGender;
    private String productFabric;
    private String productFit;
    private String productManufacturer;
    private Double price;

    @ElementCollection
    @CollectionTable(
        name = "product_size_quantity",
        joinColumns = @JoinColumn(name = "product_id")
    )
    @MapKeyColumn(name = "size")
    @Column(name = "quantity")
    private Map<String, Integer> sizeToQuantity = new HashMap<>();

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Seller seller;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductGender() {
        return productGender;
    }

    public void setProductGender(String productGender) {
        this.productGender = productGender;
    }

    public String getProductFabric() {
        return productFabric;
    }

    public void setProductFabric(String productFabric) {
        this.productFabric = productFabric;
    }

    public String getProductFit() {
        return productFit;
    }

    public void setProductFit(String productFit) {
        this.productFit = productFit;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Map<String, Integer> getSizeToQuantity() {
        return sizeToQuantity;
    }

    public void setSizeToQuantity(Map<String, Integer> sizeToQuantity) {
        this.sizeToQuantity = sizeToQuantity;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
