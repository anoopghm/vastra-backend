package com.backend.model;

import jakarta.persistence.Entity;


@Entity
public class User extends Account{
     
    String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
