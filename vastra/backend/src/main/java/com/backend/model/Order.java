package com.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
class Order{
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   Long orderId;
   //Owns the relationnship because payment is dependent on Order.
   @OneToOne(cascade=CascadeType.ALL)
   @JoinColumn(name = "payment_id")
   private PaymentDetails paymentDetails;
   private String orderStatus;
   private 

}