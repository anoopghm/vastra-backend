package com.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
class Order{
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
    Long orderId;
   

}