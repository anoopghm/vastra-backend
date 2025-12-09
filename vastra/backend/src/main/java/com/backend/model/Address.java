package com.backend.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class Address {
    @OneToOne
    @JoinColumn(name = "user")
    private User user;
   private String province;
   private String district;
   private String city;
   private String locality;
   private String landmark;
   private String phoneNumber;
   public User getUser() {
	return user;
   }
   public void setUser(User user) {
	this.user = user;
   }
   public String getProvince() {
	return province;
   }
   public void setProvince(String province) {
	this.province = province;
   }
   public String getDistrict() {
	return district;
   }
   public void setDistrict(String district) {
	this.district = district;
   }
   public String getCity() {
	return city;
   }
   public void setCity(String city) {
	this.city = city;
   }
   public String getLocality() {
	return locality;
   }
   public void setLocality(String locality) {
	this.locality = locality;
   }
   public String getLandmark() {
	return landmark;
   }
   public void setLandmark(String landmark) {
	this.landmark = landmark;
   }
   public String getPhoneNumber() {
	return phoneNumber;
   }
   public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
   }
}
