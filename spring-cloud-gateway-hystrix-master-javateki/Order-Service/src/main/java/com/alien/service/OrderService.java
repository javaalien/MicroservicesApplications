package com.alien.service;

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

	public TransactionResponse saveOrder(TransactionRequest request) {

		String response = "";
		Order order = request.getOrder();
		Payment payment = request.getPayment();

		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());

		Payment paymentResponse = restTemplate.postForObject("http://PAYMENT-SERVICE/payment/doPayment", payment,
				Payment.class);

		response = paymentResponse.getPaymentStatus().equals("success")
				? "payment processing successfull and order placed"
				: "there is failure in payment api, order added to cart";

		orderRepository.save(order);

		return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(),
				response);

	}

}
