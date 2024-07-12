package com.alien.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alien.common.Payment;
import com.alien.common.TransactionRequest;
import com.alien.common.TransactionResponse;
import com.alien.entity.Order;
import com.alien.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RefreshScope
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	@Lazy
	private RestTemplate restTemplate;

	/*
	 * @Value("${microservice.payment-service.endpoints.endpoint.uri}") private
	 * String ENDPOINT_URL;
	 */

	private Logger log = LoggerFactory.getLogger(OrderService.class);

	public TransactionResponse saveOrder(TransactionRequest request) throws JsonProcessingException {

		String response = "";
		Order order = request.getOrder();
		Payment payment = request.getPayment();

		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());

		log.info("OrderService request : {}", new ObjectMapper().writeValueAsString(request));

		Payment paymentResponse = restTemplate.postForObject("http://PAYMENT-SERVICE/payment/doPayment", payment,
				Payment.class);

		log.info("Payment response from OrderService rest call : {}",
				new ObjectMapper().writeValueAsString(paymentResponse));

		response = paymentResponse.getPaymentStatus().equals("success")
				? "payment processing successfull and order placed"
				: "there is failure in payment api, order added to cart";

		orderRepository.save(order);

		return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(),
				response);

	}

}
