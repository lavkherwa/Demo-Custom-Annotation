package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.MockService;

@RestController
@RequestMapping("/")
public class Test {

	private MockService service;

	public Test(MockService service) {
		this.service = service;
	}
	
	@RequestMapping("test")
	public void test() throws InterruptedException {
		
		service.getAllCars();
		
	}
	
}
