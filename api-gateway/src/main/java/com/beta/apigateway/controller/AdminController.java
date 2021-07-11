package com.beta.apigateway.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beta.apigateway.model.Admin;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AdminController {

	@GetMapping(produces = "application/json")
	@RequestMapping({ "/validateLogin" })
	public Admin validateLogin() {
		return new Admin("Admin successfully authenticated");
	}
	
	
	
	
}
