package com.swiggy.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.swiggy.dto.OrderResponseDTO;
import com.swiggy.exception.SwiggyServiceException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RestaurantServiceClient {

	@Autowired
	private RestTemplate template;

	public OrderResponseDTO fetchOrderStatus(String orderId) {

		OrderResponseDTO orderResponseDTO = null;

		try {

			template.getForObject("http://RESTAURANT-SERVICE/restaurant/orders/status/" + orderId,
					OrderResponseDTO.class);

		} catch (HttpServerErrorException ex) {

			log.error("RestaurantServiceClient::fetchOrderStatus caught the HttpServer server error {}",
					ex.getResponseBodyAsString());
			throw new SwiggyServiceException(ex.getResponseBodyAsString());
		} catch (Exception ex) {
			log.error("RestaurantServiceClient::fetchOrderStatus caught the generic error {}", ex.getMessage());

		}
		return orderResponseDTO;
	}
}
