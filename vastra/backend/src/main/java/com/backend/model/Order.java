package com.backend.model;

import java.util.List;

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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_address_id", nullable = false)
    private OrderAddress deliveryAddress;
   private String orderStatus;
   private List<CartItem>orders;
   
   public PaymentDetails getPaymentDetails() {
      return paymentDetails;
   }
   public void setPaymentDetails(PaymentDetails paymentDetails) {
      this.paymentDetails = paymentDetails;
   }
   public OrderAddress getDeliveryAddress() {
      return deliveryAddress;
   }
   public void setDeliveryAddress(OrderAddress deliveryAddress) {
      this.deliveryAddress = deliveryAddress;
   }
   public String getOrderStatus() {
      return orderStatus;
   }
   public void setOrderStatus(String orderStatus) {
      this.orderStatus = orderStatus;
   }
   public List<CartItem> getOrders() {
      return orders;
   }
   public void setOrders(List<CartItem> orders) {
      this.orders = orders;
   }
   public Long getOrderId() {
      return orderId;
   }

}