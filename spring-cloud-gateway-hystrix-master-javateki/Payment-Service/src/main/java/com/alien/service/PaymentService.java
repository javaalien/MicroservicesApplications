package com.alien.service;

import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alien.entity.Payment;
import com.alien.repository.PaymentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	Logger log = LoggerFactory.getLogger(PaymentService.class);

	public Payment doPayment(Payment payment) throws JsonProcessingException {
		payment.setPaymentStatus(paymentProcessing());

		payment.setTransactionId(UUID.randomUUID().toString());
		log.info("PaymentService request : {}", new ObjectMapper().writeValueAsString(payment));
		return paymentRepository.save(payment);
	}

	public String paymentProcessing() {
		// api should be 3rd party payment gateway (paypal,paytm...)
		return new Random().nextBoolean() ? "success" : "false";
	}

	public Payment findPaymentHistoryByOrderId(int orderId) throws JsonProcessingException {
		Payment payment = paymentRepository.findByOrderId(orderId);
		log.info("paymentService findPaymentHistoryByOrderId : {}", new ObjectMapper().writeValueAsString(payment));
		return payment;
	}

}
