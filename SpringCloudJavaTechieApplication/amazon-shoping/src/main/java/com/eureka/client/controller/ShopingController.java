package com.eureka.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ShopingController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/amazon-payment/{price}")
	public String invokeService(@PathVariable int price) {
		String url = "http://PAYMENT-SERVICE/payment/payNow/" + price;
		
		return restTemplate.getForObject(url, String.class);
	}
}
