package com.backend.services;

public interface SellerServices {
     public void addSeller(String name, String email, String password, String phoneNumber, String address);
     public void updateSeller(Long id, String name, String email, String password, String phoneNumber, String address);
     public void deleteSeller(Long id);
     public void getSellerById(Long id);
     public void getAllSellers();
}
