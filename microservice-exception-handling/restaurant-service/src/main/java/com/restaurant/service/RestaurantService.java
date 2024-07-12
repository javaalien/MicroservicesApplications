package com.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.dao.RestaurantOrderDAO;
import com.restaurant.dto.OrderResponseDTO;
import com.restaurant.exception.OrderNotFoundException;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantOrderDAO orderDAO;

	public String greeting() {
		return "Welcome to Swiggy Restaurant service";
	}

	public OrderResponseDTO getOrder(String orderId) {

		OrderResponseDTO orderResponseDTO = orderDAO.getOrders(orderId);

		if (orderResponseDTO != null) {
			return orderResponseDTO;
		} else {
			throw new OrderNotFoundException("Order not available with id : " + orderId);
		}

	}

}
