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
   public Long getPaymentId() {
    return paymentId;
   }
   public Order getOrder() {
    return order;
   }
   public void setOrder(Order order) {
    this.order = order;
   }
   public Date getDate() {
    return date;
   }
   public void setDate(Date date) {
    this.date = date;
   }
   public boolean isPaymentDone() {
    return paymentDone;
   }
   public void setPaymentDone(boolean paymentDone) {
    this.paymentDone = paymentDone;
   }
}