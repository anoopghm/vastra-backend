package com.backend.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
class PaymentDetails{
    @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   Long paymentId;

   @OneToOne(mappedBy = "paymentDetails")
   private Order order;
   private Date date;
   boolean paymentDone = false;
   

}