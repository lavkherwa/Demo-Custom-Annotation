package com.example.demo.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.annotation.TrackExecutionTime;

@Service
public class MockService {

	List<String> carList = Arrays.asList("Audi", "BMW", "Porsche", "Mercedes");

	// check how much time takes to get a CAR
	@TrackExecutionTime
	public List<String> getAllCars() throws InterruptedException {

		Thread.sleep(10000); // 10 seconds

		return Collections.unmodifiableList(carList);
	}

	public void addCar() {
		// do nothing 
	}

}
