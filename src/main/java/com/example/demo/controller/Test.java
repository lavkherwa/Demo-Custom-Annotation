package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public void test() throws InterruptedException {

		service.getAllCars();

	}

	@PostMapping("test")
	public void test2(@RequestBody @Valid Car car) {

		service.addCar();
	}

}
