package com.swiggy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swiggy.dto.OrderResponseDTO;
import com.swiggy.service.SwiggyAppService;

@RestController
@RequestMapping("/swiggy")
public class SwiggyAppController {

	@Autowired
	private SwiggyAppService service;

	@GetMapping("/home")
	public String greetingMessage() {
		return service.greeting();
	}

	@GetMapping("/{orderId}")
	public OrderResponseDTO checkOrderStatus(@PathVariable String orderId) {
		return service.checkOrderStatus(orderId);
	}

}
