package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.Car;
import com.example.demo.service.MockService;

@RestController
@RequestMapping("/")
@Validated
public class Test {

	private MockService service;

	public Test(MockService service) {
		this.service = service;
	}

	@RequestMapping("test")
	public void testCars() throws InterruptedException {

		service.getAllCars();

	}

	@RequestMapping("test2")
	public String testCar(@RequestParam String name) throws InterruptedException {

		return service.getCar(name, "test");

	}

	@PostMapping("test")
	public void testCars(@RequestBody @Valid Car car) {

		service.addCar();
	}

}
