package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long>{
    
}