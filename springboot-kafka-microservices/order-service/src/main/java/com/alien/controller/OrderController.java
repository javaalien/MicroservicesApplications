package com.alien.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alien.dto.Order;
import com.alien.dto.OrderEvent;
import com.alien.kafka.OrderProducer;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	private OrderProducer orderProducer;

	public OrderController(OrderProducer orderProducer) {
		this.orderProducer = orderProducer;
	}

	@PostMapping("/orders")
	public String placeOrder(@RequestBody Order order) {
		order.setOrderId(UUID.randomUUID().toString());

		OrderEvent orderEvent = new OrderEvent();

		orderEvent.setStatus("PENDING");
		orderEvent.setMessage("order status is in pending state");
		orderEvent.setOrder(order);

		orderProducer.sendMessage(orderEvent);

		return "Order placed successfully";
	}
}
