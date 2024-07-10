package com.alien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alien.common.Payment;
import com.alien.common.TransactionRequest;
import com.alien.common.TransactionResponse;
import com.alien.entity.Order;
import com.alien.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/bookOrder")
	public TransactionResponse bookOrder(@RequestBody TransactionRequest transactionRequest) {
		
		return orderService.saveOrder(transactionRequest);
	}

}
