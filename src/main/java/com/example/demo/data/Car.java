package com.example.demo.data;

import com.example.demo.annotation.ValidateMake;

public class Car {

	private String name;

	@ValidateMake(message="Make not valid")
	private String make;

	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

}
